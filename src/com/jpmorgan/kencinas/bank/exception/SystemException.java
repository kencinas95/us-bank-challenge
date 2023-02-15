package com.jpmorgan.kencinas.bank.exception;

public class SystemException extends RuntimeException {
    private int code;

    private String message;

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SystemException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }
}
