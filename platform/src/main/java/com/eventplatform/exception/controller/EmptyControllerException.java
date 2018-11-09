package com.eventplatform.exception.controller;

public class EmptyControllerException extends Exception {
    public EmptyControllerException() {
        super();
    }

    public EmptyControllerException(String message) {
        super(message);
    }
}
