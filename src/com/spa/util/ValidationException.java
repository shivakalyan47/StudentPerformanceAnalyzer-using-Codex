package com.spa.util;

/**
 * Custom exception used when invalid input is entered in the form.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
