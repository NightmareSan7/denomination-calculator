package com.example.denomination.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import java.math.BigDecimal;

public class DenominationDifferenceEntryTest {

    @Test
    public void testAttributes() {
        Assertions.assertEquals(2, DenominationDifferenceEntry.class.getDeclaredFields().length);
    }

    @Test
    public void testSerialization() {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        Assertions.assertEquals("{\"denomination\":5.25,\"differenceCount\":2}", objectWriter.writeValueAsString(new DenominationDifferenceEntry(new BigDecimal("5.25"), 2)));
    }
}
