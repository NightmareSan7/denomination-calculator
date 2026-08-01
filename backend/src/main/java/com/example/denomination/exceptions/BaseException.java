package com.example.denomination.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends Exception {
    private String error;
    private String errorTag;
    private String errorDetails;
}
