package com.apollocurrrency.aplwallet.inttest.tests;

import com.apollocurrency.aplwallet.api.response.CreateTransactionResponse;
import com.apollocurrrency.aplwallet.inttest.helper.TestConfiguration;
import com.apollocurrrency.aplwallet.inttest.model.TestBaseOld;
import com.apollocurrrency.aplwallet.inttest.model.TransactionTypes;
import com.apollocurrrency.aplwallet.inttest.model.Wallet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.concurrent.TimeUnit;

public class TestPhasing extends TestBaseOld {
    private final Wallet wallet = TestConfiguration.getTestConfiguration().getStandartWallet();


    @DisplayName("Referenced Transaction")
    @ParameterizedTest(name = "{displayName} Transaction Type: {arguments}")
    @EnumSource(TransactionTypes.class)
    @Disabled
    void referencedTransactionTest(TransactionTypes type){
        CreateTransactionResponse createTransaction = null;

        switch (type){
            case SEND_MONEY:
                createTransaction =  sendMoney(wallet,wallet.getUser(),100);
                break;

        }
    }

    @DisplayName("Phased Transaction")
    @ParameterizedTest(name = "{displayName} Transaction Type: {arguments}")
    @EnumSource(TransactionTypes.class)
    void phasedTest(TransactionTypes type){
       // phased(getBlock().getHeight());
        CreateTransactionResponse createTransaction = null;
        switch (type){
            case SEND_MONEY:
                createTransaction =  sendMoney(wallet,wallet.getUser(),100);
                break;
            case CREATE_POLL:
                break;
            case SEND_MESSAGE:
                break;
        }

    }
}
