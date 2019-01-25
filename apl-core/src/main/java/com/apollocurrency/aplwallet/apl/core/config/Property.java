/*
 * Copyright © 2018 Apollo Foundation
 */

package com.apollocurrency.aplwallet.apl.core.config;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Represents a property key to be injected
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface Property {
    @Nonbinding String name() default "";
/** <p>Use this parameter for testnet property name.</p>
    <p>Note, that is a temporary solution while project refactoring is in-progress</p>
 */
    @Nonbinding String testnetName() default "";
    @Nonbinding String defaultValue() default "";
}