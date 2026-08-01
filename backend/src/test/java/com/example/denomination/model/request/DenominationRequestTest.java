package com.example.denomination.model.request;

import com.example.denomination.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import java.math.BigDecimal;

public class DenominationRequestTest {

    @Test
    public void testAttributes() {
        Assertions.assertEquals(2, DenominationRequest.class.getDeclaredFields().length);
    }

    @Test
    public void testSerialization() {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        Assertions.assertEquals("{\"currentAmount\":5.00,\"previousAmount\":null}", objectWriter.writeValueAsString(new DenominationRequest(new BigDecimal("5.00"), null)));
    }

    @Test
    public void testValidateRequestValuesSuccess() {
        Assertions.assertDoesNotThrow(() -> new DenominationRequest(new BigDecimal("5.000"), null).validateRequestValues());

        Assertions.assertDoesNotThrow(() -> new DenominationRequest(new BigDecimal("5.00"), new BigDecimal("253")).validateRequestValues());

    }

    @Test
    public void testValidateRequestValuesError() {

        ValidationException ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(null, null).validateRequestValues());
        verifyException("current", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("0"), null).validateRequestValues());
        verifyException("current", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("-1"), null).validateRequestValues());
        verifyException("current", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("1.555"), null).validateRequestValues());
        verifyException("current", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("5"), new BigDecimal("-5")).validateRequestValues());
        verifyException("previous", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("5"), new BigDecimal("0")).validateRequestValues());
        verifyException("previous", ve);

        ve = Assertions.assertThrows(ValidationException.class, () -> new DenominationRequest(new BigDecimal("5"), new BigDecimal("5.5555")).validateRequestValues());
        verifyException("previous", ve);
    }

    private void verifyException(String type, ValidationException ve) {
        Assertions.assertNotNull(ve);
        Assertions.assertEquals("V000000001", ve.getError());
        Assertions.assertEquals(type + " amount has to be positive and have a max. of two decimal places!", ve.getErrorDetails());
        Assertions.assertEquals("TOOLING.DENOMINATION.REQUEST.INVALID", ve.getErrorTag());
    }
}
