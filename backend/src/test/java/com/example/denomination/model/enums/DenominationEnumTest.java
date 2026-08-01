package com.example.denomination.model.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class DenominationEnumTest {

    @Test
    public void testDenominationAmount() {
        Assertions.assertEquals(14, DenominationEnum.values().length);
    }

    @Test
    public void testDenominationValues() {
        Assertions.assertEquals(new BigDecimal("200"), DenominationEnum.EUR_200.getValue());
        Assertions.assertEquals(new BigDecimal("100"), DenominationEnum.EUR_100.getValue());
        Assertions.assertEquals(new BigDecimal("50"), DenominationEnum.EUR_50.getValue());
        Assertions.assertEquals(new BigDecimal("20"), DenominationEnum.EUR_20.getValue());
        Assertions.assertEquals(new BigDecimal("10"), DenominationEnum.EUR_10.getValue());
        Assertions.assertEquals(new BigDecimal("5"), DenominationEnum.EUR_5.getValue());
        Assertions.assertEquals(new BigDecimal("2"), DenominationEnum.EUR_2.getValue());
        Assertions.assertEquals(new BigDecimal("1"), DenominationEnum.EUR_1.getValue());
        Assertions.assertEquals(new BigDecimal("0.50"), DenominationEnum.CENT_50.getValue());
        Assertions.assertEquals(new BigDecimal("0.20"), DenominationEnum.CENT_20.getValue());
        Assertions.assertEquals(new BigDecimal("0.10"), DenominationEnum.CENT_10.getValue());
        Assertions.assertEquals(new BigDecimal("0.05"), DenominationEnum.CENT_5.getValue());
        Assertions.assertEquals(new BigDecimal("0.02"), DenominationEnum.CENT_2.getValue());
        Assertions.assertEquals(new BigDecimal("0.01"), DenominationEnum.CENT_1.getValue());
    }

    @Test
    public void testOrderedByDescending() {
        List<DenominationEnum> denominations = DenominationEnum.orderedByDescending();

        for (int i = 0; i < denominations.size() - 1; i++) {
            Assertions.assertTrue(denominations.get(i).getValue().compareTo(denominations.get(i + 1).getValue()) > 0);
        }
    }
}
