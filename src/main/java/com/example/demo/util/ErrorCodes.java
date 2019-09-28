package com.example.demo.util;

public enum ErrorCodes {

    ID_NOT_FOUND("ID_NOT_FOUND", "Person's id could not be found", "id"),
    BAD_REQUEST_MINIMUM_AGE("BAD_REQUEST", "Age must be minimum 18", "age"),
    BAD_REQUEST_LASTNAME("BAD_REQUEST", "Last name should only contain letters and -", "lastName"),
    BAD_REQUEST_FIRSTNAME("BAD_REQUEST", "First name should only contain letters and -", "firstName"),
    BAD_REQUEST_AGE_FORMAT("BAD_REQUEST", "Age should only contain numbers", "age");



    private String errorCode;
    private String errorMessage;
    private String errorScope;

    ErrorCodes(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorScope = null;
    }

    ErrorCodes(String errorCode, String errorMessage, String errorMsgScope) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorScope = errorMsgScope;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorScope() {
        return errorScope;
    }
}