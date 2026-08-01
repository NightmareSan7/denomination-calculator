package com.example.denomination.exceptions;

import com.example.denomination.model.enums.ValidationCodesEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationException extends BaseException {
    public ValidationException(ValidationCodesEnum validationCodesEnum, String message) {
        this.setError(validationCodesEnum.getName());
        this.setErrorTag(validationCodesEnum.getTag());
        this.setErrorDetails(message);
    }
}
