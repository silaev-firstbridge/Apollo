package com.apollocurrency.aplwallet.apl.util.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
class BackgroundTaskDispatcherTest {

    private TaskDispatcher taskDispatcher;
    private Runnable runnable;
    private Task task;

    @BeforeEach
    void setUp() {
        runnable = mock(Runnable.class);
        task = Task.builder()
                .name("TestTask")
                .delay(10)
                .initialDelay(0)
                .task(runnable)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void scheduleAtFixedRate() {
        taskDispatcher = TaskDispatcherFactory.newScheduledDispatcher("TestThreadInfo");
        task.setTask(runnable);

        taskDispatcher.schedule(task);

        taskDispatcher.dispatch();
        log.info("Thread dispatch");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //expected that thread executed at least 9 times
        verify(runnable, atLeast(9)).run();
    }

    @Test
    void scheduleBeforeAndAfterScheduleTasks() {
        taskDispatcher = TaskDispatcherFactory.newBackgroundDispatcher("TestTransactionService");
        final Count count0 = new Count(10);
        final Count count1 = new Count(10);

        Task task0 = Task.builder()
                .name("task-INIT1")
                .task(()-> {count0.dec(); log.info("task-body: INIT task running");})
                .initialDelay(0)
                .build();

        Task task1 = Task.builder()
                .name("task-BEFORE1")
                .task(()-> {count0.dec(); log.info("task-body: BEFORE task 1 running");})
                .initialDelay(10)
                .build();
        Task task12 = Task.builder()
                .name("task-BEFORE12")
                .task(()-> {count0.dec(); log.info("task-body: BEFORE task 12 running");})
                .initialDelay(20)
                .build();
        Task task2 = Task.builder()
                .name("task-AFTER1")
                .task(()-> {count0.dec();log.info("task-body: AFTER task 1 running");})
                .delay(10)
                .build();
        Task task22 = Task.builder()
                .name("task-AFTER12")
                .task(()-> {count0.dec();log.info("task-body: AFTER task 2 running");})
                .delay(20)
                .build();
        Task taskMain = Task.builder()
                .name("MainTask-sleep-main")
                .task(()->{
                    for (;;){
                        assertTrue(count0.get()<=7);//10-3 = 7; 3 tasks={INIT, BEFORE1, BEFORE12}
                        log.info("task-body: MAIN task running, thread={}", getThreadInfo());
                        count1.dec();
                        log.info("task-body: count0={} count1={}", count0.get(), count1.get());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                })
                .delay(20)
                .build();

        taskDispatcher.schedule(taskMain, TaskOrder.TASK);
        taskDispatcher.schedule(task0, TaskOrder.INIT);
        taskDispatcher.schedule(task1, TaskOrder.BEFORE);
        taskDispatcher.schedule(task12, TaskOrder.BEFORE);
        taskDispatcher.schedule(task2, TaskOrder.AFTER);
        taskDispatcher.schedule(task22, TaskOrder.AFTER);

        taskDispatcher.dispatch();
        log.info("Thread dispatch");

        try {
            Thread.sleep(250);
        } catch (InterruptedException ignored) {}

        assertTrue(count1.get()<9, "Exception was occurred in the Main task.");
        assertTrue(count0.get()<6);

    }

    @Test
    void whenScheduleAfterShutdown_thenGetException() {
        taskDispatcher = TaskDispatcherFactory.newScheduledDispatcher("TestThreadInfo");
        taskDispatcher.schedule(task);
        taskDispatcher.dispatch();
        log.info("Thread dispatch");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}

        taskDispatcher.shutdown();
        assertThrows(RejectedExecutionException.class, () -> taskDispatcher.schedule(task));

        //expected that thread executed at least 1 time
        verify(runnable, atLeast(1)).run();
    }

    private static String getThreadInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("ThreadGroup Name: ").append(Thread.currentThread().getThreadGroup().getName());
        sb.append(" Thread: ").append(Thread.currentThread().toString());
        return sb.toString();
    }

    static class Count {
        private int value;

        public Count(int value) {
            this.value = value;
        }

        public int inc(){
            return value++;
        }

        public int dec(){
            return value--;
        }

        public int get(){
            return value;
        }

    }
}