package com.jpmorgan.kencinas.bank.exception;

public class ShortPositionAlertException extends SystemException {
    public ShortPositionAlertException() {
        super(100, "Short position alert!");
    }
}
