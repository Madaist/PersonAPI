package com.example.demo.controller;

import com.example.demo.util.Error;
import com.example.demo.util.ErrorCodes;
import com.example.demo.util.ServiceException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final String RESOURCE_BUNDLE_ERROR_MESSAGES = "error.bundle.errorMessages";
    private final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    private final HttpServletRequest request;


    public ExceptionAdvice(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Error> handlerNumberFormatException(NumberFormatException ex) {

        Error error = new Error("Number format exception " + ex.getMessage(), "BAD_REQUEST" );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Error> handlerFormatException(InvalidFormatException ex) {

        Error error = new Error("Invalid format exception " + ex.getMessage(), "BAD_REQUEST");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Error> handlerServiceException(ServiceException ex) {

        String message = getResourceBundleMessageByCode(ex.getError());
        message = (message == null) ? ex.getError().getErrorMessage() : message;

        switch (ex.getError()) {
            case ID_NOT_FOUND:
                return new ResponseEntity<>(new Error(message, ex.getError().getErrorCode(),
                        ex.getError().getErrorScope()),
                        HttpStatus.NOT_FOUND);
            case BAD_REQUEST_MINIMUM_AGE:
            case BAD_REQUEST_LASTNAME:
            case BAD_REQUEST_FIRSTNAME:
            case BAD_REQUEST_AGE_FORMAT:
                return new ResponseEntity<>(new Error(message, ex.getError().getErrorCode(),
                        ex.getError().getErrorScope()),
                        HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getResourceBundleMessageByCode(ErrorCodes code) {
        Locale locale = new Locale("en");

        if (!StringUtils.isEmpty(request.getHeader("Accept-Language"))
                && request.getHeader("Accept-Language").toLowerCase().contains("ro")) {
            locale = new Locale("ro");
        }

        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_ERROR_MESSAGES, locale);
        try {
            return bundle.getString(code.name());
        } catch (Exception e) {
            logger.error("Bundle doesn't have this error code defined");
            return null;
        }
    }


}
