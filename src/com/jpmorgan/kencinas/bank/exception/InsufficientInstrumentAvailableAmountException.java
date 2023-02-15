package com.jpmorgan.kencinas.bank.exception;

public class InsufficientInstrumentAvailableAmountException extends SystemException {
    public InsufficientInstrumentAvailableAmountException() {
        super(101, "Insufficient instrument available amount!");
    }
}
