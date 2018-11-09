package com.eventplatform.exception.controller;

public class AlreadyExistControllerException extends Exception {
    public AlreadyExistControllerException() {
        super();
    }

    public AlreadyExistControllerException(String message) {
        super(message);
    }
}
