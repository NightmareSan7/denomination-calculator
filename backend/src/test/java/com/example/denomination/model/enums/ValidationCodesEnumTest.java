package com.example.denomination.model.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationCodesEnumTest {

    @Test
    public void testValidationCodesAmount() {
        Assertions.assertEquals(1, ValidationCodesEnum.values().length);
    }
}
