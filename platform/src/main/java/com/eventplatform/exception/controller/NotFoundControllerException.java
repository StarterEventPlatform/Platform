package com.eventplatform.exception.controller;

public class NotFoundControllerException extends Exception {
    public NotFoundControllerException() {
        super();
    }

    public NotFoundControllerException(String message) {
        super(message);
    }
}
