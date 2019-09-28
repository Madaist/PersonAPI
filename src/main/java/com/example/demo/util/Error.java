package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Error {

    private final String errorMessage;
    private final String errorCode;
    private final String errorScope;


    public Error(String errorMessage, String errorCode, String errorScope) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorScope = errorScope;
    }

    public Error(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorScope = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorScope() {
        return errorScope;
    }

    @Override
    public String toString() {
        return "Error{"
                + "errorMessage='" + errorMessage + '\''
                + ", errorCode='" + errorCode + '\''
                + ", errorScope='" + errorScope + '\''
                + '}';
    }
}
