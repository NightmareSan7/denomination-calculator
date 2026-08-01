package com.example.denomination.service;

import com.example.denomination.exceptions.ValidationException;
import com.example.denomination.model.DenominationDifferenceEntry;
import com.example.denomination.model.DenominationEntry;
import com.example.denomination.model.request.DenominationRequest;
import com.example.denomination.model.response.DenominationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class DenominationServiceTest {

    @Autowired
    private DenominationService denominationService;

    @TestConfiguration
    static class DenominationServiceTestContextConfiguration {

        @Bean
        public DenominationService denominationService() {
            return new DenominationService();
        }
    }

    private DenominationEntry denominationEntry(String denomination, long count) {
        return new DenominationEntry(new BigDecimal(denomination), count);
    }

    private DenominationDifferenceEntry differenceEntry(String denomination, long differenceCount) {
        return new DenominationDifferenceEntry(new BigDecimal(denomination), differenceCount);
    }

    @Test
    public void testProcessDenominationWithPreviousValue() throws ValidationException {
        DenominationRequest denominationRequest = new DenominationRequest(new BigDecimal("234.23"), new BigDecimal("45.32"));
        DenominationResponse denominationResponse = denominationService.processDenomination(denominationRequest);
        DenominationResponse expectedResponse = new DenominationResponse(
                List.of(
                        denominationEntry("200", 1),
                        denominationEntry("20", 1),
                        denominationEntry("10", 1),
                        denominationEntry("2", 2),
                        denominationEntry("0.20", 1),
                        denominationEntry("0.02", 1),
                        denominationEntry("0.01", 1)
                ),
                List.of(
                        differenceEntry("200", 1),
                        differenceEntry("20", -1),
                        differenceEntry("10", 1),
                        differenceEntry("5", -1),
                        differenceEntry("2", 2),
                        differenceEntry("0.20", 0),
                        differenceEntry("0.10", -1),
                        differenceEntry("0.02", 0),
                        differenceEntry("0.01", 1)));
        Assertions.assertEquals(expectedResponse, denominationResponse);
    }

    @Test
    public void testProcessDenominationWithNoPreviousValue() throws ValidationException {
        DenominationRequest denominationRequest = new DenominationRequest(new BigDecimal("234.23"), null);
        DenominationResponse denominationResponse = denominationService.processDenomination(denominationRequest);
        DenominationResponse expectedResponse = new DenominationResponse(
                List.of(
                        denominationEntry("200", 1),
                        denominationEntry("20", 1),
                        denominationEntry("10", 1),
                        denominationEntry("2", 2),
                        denominationEntry("0.20", 1),
                        denominationEntry("0.02", 1),
                        denominationEntry("0.01", 1)
                ),
                List.of());
        Assertions.assertEquals(expectedResponse, denominationResponse);
    }

    @Test
    public void testProcessDenominationMultipleValues() throws ValidationException {
        DenominationResponse denominationResponse = denominationService.processDenomination(new DenominationRequest(new BigDecimal("0.01"), null));
        DenominationResponse expectedResponse = new DenominationResponse(List.of(denominationEntry("0.01", 1)), List.of());
        Assertions.assertEquals(expectedResponse, denominationResponse);
        denominationResponse = denominationService.processDenomination(new DenominationRequest(new BigDecimal("255"), new BigDecimal("255")));

        expectedResponse = new DenominationResponse(
                List.of(
                        denominationEntry("200", 1),
                        denominationEntry("50", 1),
                        denominationEntry("5", 1)
                ),
                List.of(
                        differenceEntry("200", 0),
                        differenceEntry("50", 0),
                        differenceEntry("5", 0)));
        Assertions.assertEquals(expectedResponse, denominationResponse);
    }

}
