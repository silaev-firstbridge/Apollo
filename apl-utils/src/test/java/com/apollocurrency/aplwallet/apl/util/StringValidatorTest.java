package com.apollocurrency.aplwallet.apl.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringValidatorTest {

    @Test
    void requireNonBlankOk1() {
        StringValidator.requireNonBlank("NotBlank");
        assertEquals("NotBlank", "NotBlank");
    }

    @Test
    void requireNonBlankError1() {
        assertThrows(NullPointerException.class, () -> {
            StringValidator.requireNonBlank(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireNonBlank("   ");
        });
    }

    @Test
    void testRequireNonBlankOk2() {
        StringValidator.requireNonBlank("NotBlank", "Parameter1");
        assertEquals("NotBlank", "NotBlank");
        StringValidator.requireNonBlank("Value1", null);
        assertEquals("Value1", "Value1");
    }

    @Test
    void testRequireNonBlankError2() {
        assertThrows(NullPointerException.class, () -> {
            StringValidator.requireNonBlank(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            StringValidator.requireNonBlank(null, "Param1");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireNonBlank("   ", "Param1");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireNonBlank("   ", "  ");
        });
    }

    @Test
    void testRequireMultipleNonBlankOk() {
        String[] result = StringValidator.requireMultipleNonBlank("NotBlankParamName", "Value1", "Value2", "Value3");
        assertEquals("NotBlankParamName", "NotBlankParamName");
        assertEquals(3, result.length);
        assertEquals("Value1", result[0]);
        assertEquals("Value2", result[1]);
        assertEquals("Value3", result[2]);
    }

    @Test
    void testRequireMultipleNonBlankError() {
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank(null, null, null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank("   ", null, null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank("ParamName", null, null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank("ParamName", " ", "Value2", "Value3");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank("ParamName", "Value1", "  ", "Value3");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.requireMultipleNonBlank("ParamName", "Value1", "Value2", "  ");
        });
    }
}