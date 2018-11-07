package com.eventplatform.exception.utils;

import java.io.IOException;

public class JsonParserException extends IOException {
    public JsonParserException() {
        super();
    }

    public JsonParserException(String message) {
        super(message);
    }
}
