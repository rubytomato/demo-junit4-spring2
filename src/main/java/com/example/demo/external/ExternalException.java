package com.example.demo.external;

public class ExternalException extends RuntimeException {

    public ExternalException() {
    }

    public ExternalException(String message) {
        super(message);
    }

    public ExternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalException(Throwable cause) {
        super(cause);
    }

}
