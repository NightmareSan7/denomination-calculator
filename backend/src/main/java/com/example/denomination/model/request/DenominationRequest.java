package com.example.denomination.model.request;

import com.example.denomination.exceptions.ValidationException;
import com.example.denomination.model.enums.ValidationCodesEnum;

import java.math.BigDecimal;

public record DenominationRequest(BigDecimal currentAmount, BigDecimal previousAmount) {

    /**
     * Validates the Values within the Request
     * <p> currentAmount: > 0, not null and max. of 2 decimal places
     * <p> previousAmount: > 0, can be null and have max. of 2 decimal places
     *
     * @throws ValidationException if validation fails
     */
    public void validateRequestValues() throws ValidationException {
        if (this.currentAmount() == null || this.currentAmount().compareTo(BigDecimal.ZERO) <= 0 || this.currentAmount().stripTrailingZeros().scale() > 2) {
            throw new ValidationException(ValidationCodesEnum.V000000001, "current amount has to be positive and have a max. of two decimal places!");
        }

        if (this.previousAmount() != null && (this.previousAmount().compareTo(BigDecimal.ZERO) <= 0 || this.previousAmount().stripTrailingZeros().scale() > 2)) {
            throw new ValidationException(ValidationCodesEnum.V000000001, "previous amount has to be positive and have a max. of two decimal places!");
        }

    }
}
