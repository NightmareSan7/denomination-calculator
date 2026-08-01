package com.example.denomination.model.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorCodesEnumTest {

    @Test
    public void testErrorCodesAmount() {
        Assertions.assertEquals(1, ErrorCodesEnum.values().length);
    }
}
