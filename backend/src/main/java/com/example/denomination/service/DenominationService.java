package com.example.denomination.service;

import com.example.denomination.exceptions.ValidationException;
import com.example.denomination.model.DenominationDifferenceEntry;
import com.example.denomination.model.DenominationEntry;
import com.example.denomination.model.enums.DenominationEnum;
import com.example.denomination.model.request.DenominationRequest;
import com.example.denomination.model.response.DenominationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DenominationService {

    /**
     * Will process the required denomination for the provided amount and if the previous amount exists, will calculate the difference compared to the current amount.
     *
     * @param denominationRequest the object provided from the API Request
     * @return {@link DenominationResponse}
     */
    public DenominationResponse processDenomination(DenominationRequest denominationRequest) throws ValidationException {
        denominationRequest.validateRequestValues();
        List<DenominationEntry> currentDenominations = calculateDenomination(denominationRequest.currentAmount());
        List<DenominationDifferenceEntry> denominationDifferences = new ArrayList<>();
        if (denominationRequest.previousAmount() != null) {
            List<DenominationEntry> previousDenominations = calculateDenomination(denominationRequest.previousAmount());
            denominationDifferences = calculateDenominationDifference(currentDenominations, previousDenominations);
        }

        return new DenominationResponse(currentDenominations.stream().filter(denominationEntry -> denominationEntry.count() > 0).toList(), denominationDifferences);
    }

    /**
     * Calculates the denomination based on the provided amount.
     *
     * @param amount
     * @return List<{@link DenominationEntry}>
     */
    private List<DenominationEntry> calculateDenomination(BigDecimal amount) {
        List<DenominationEntry> denominationEntries = new ArrayList<>();

        for (DenominationEnum denomination : DenominationEnum.orderedByDescending()) {
            BigDecimal[] result = amount.divideAndRemainder(denomination.getValue());
            denominationEntries.add(new DenominationEntry(denomination.getValue(), result[0].longValueExact()));
            amount = result[1];
        }

        return denominationEntries;

    }

    /**
     * Compares the provided lists and returns a List with differences to the used denominations
     *
     * @param currentDenominations
     * @param previousDenominations
     * @return List<{@link DenominationDifferenceEntry}>
     */
    private List<DenominationDifferenceEntry> calculateDenominationDifference(List<DenominationEntry> currentDenominations, List<DenominationEntry> previousDenominations) {
        List<DenominationDifferenceEntry> denominationDifferences = new ArrayList<>();

        for (int i = 0; i < currentDenominations.size(); i++) {
            DenominationEntry current = currentDenominations.get(i);
            DenominationEntry previous = previousDenominations.get(i);
            if (current.count() > 0 || previous.count() > 0) {
                denominationDifferences.add(new DenominationDifferenceEntry(current.denomination(), current.count() - previous.count()));
            }
        }
        return denominationDifferences;
    }
}
