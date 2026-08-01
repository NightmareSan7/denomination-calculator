package com.example.denomination.model.response;

import com.example.denomination.model.DenominationDifferenceEntry;
import com.example.denomination.model.DenominationEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import java.math.BigDecimal;
import java.util.List;

public class DenominationResponseTest {

    @Test
    public void testAttributes() {
        Assertions.assertEquals(2, DenominationResponse.class.getDeclaredFields().length);
    }

    @Test
    public void testSerialization() {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        Assertions.assertEquals(
                "{\"denominations\":[{\"denomination\":5,\"count\":1}],\"differences\":[{\"denomination\":5,\"differenceCount\":1},{\"denomination\":2,\"differenceCount\":-1}]}",
                objectWriter.writeValueAsString(
                        new DenominationResponse(
                                List.of(new DenominationEntry(new BigDecimal("5"), 1)),
                                List.of(new DenominationDifferenceEntry(new BigDecimal("5"), 1),
                                        new DenominationDifferenceEntry(new BigDecimal("2"), -1)))));
    }

}
