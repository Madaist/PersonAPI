package com.example.demo.util;

public class ServiceException extends RuntimeException {

    private final ErrorCodes error;


    public ServiceException(ErrorCodes error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public ErrorCodes getError() {
        return error;
    }
}


