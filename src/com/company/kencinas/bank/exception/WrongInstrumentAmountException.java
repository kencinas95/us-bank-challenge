package com.company.kencinas.bank.exception;

public class WrongInstrumentAmountException extends SystemException {
    public WrongInstrumentAmountException() {
        super(100, "Wrong instrument exception: must be integer, got decimal.");
    }
}
