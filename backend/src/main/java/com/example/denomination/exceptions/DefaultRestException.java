package com.example.denomination.exceptions;


import com.example.denomination.model.enums.ErrorCodesEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultRestException extends BaseException {
    private boolean success;
    private int status;
    private boolean validationError;

    public DefaultRestException(ErrorCodesEnum errorCodesEnum) {
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setSuccess(false);
        this.setError(errorCodesEnum.getName());
        this.setErrorTag(errorCodesEnum.getTag());
        this.setErrorDetails(errorCodesEnum.getDescription());
        this.setValidationError(false);
    }

    public DefaultRestException(ValidationException ve) {
        this.setStatus(HttpStatus.BAD_REQUEST.value());
        this.setSuccess(false);
        this.setError(ve.getError());
        this.setErrorTag(ve.getErrorTag());
        this.setErrorDetails(ve.getErrorDetails());
        this.setValidationError(true);
    }
}
