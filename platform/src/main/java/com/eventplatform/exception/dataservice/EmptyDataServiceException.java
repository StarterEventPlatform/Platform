package com.eventplatform.exception.dataservice;

public class EmptyDataServiceException extends Exception {
    public EmptyDataServiceException() {
        super();
    }

    public EmptyDataServiceException(String message) {
        super(message);
    }
}
