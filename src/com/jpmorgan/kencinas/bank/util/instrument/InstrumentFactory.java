package com.jpmorgan.kencinas.bank.util.instrument;

import com.jpmorgan.kencinas.bank.exception.SystemException;
import com.jpmorgan.kencinas.bank.exception.WrongInstrumentAmountException;
import com.jpmorgan.kencinas.bank.model.Instrument;
import com.jpmorgan.kencinas.bank.model.instrument.Bond;
import com.jpmorgan.kencinas.bank.model.instrument.Stock;
import com.jpmorgan.kencinas.bank.model.instrument.cash.Euro;
import com.jpmorgan.kencinas.bank.model.instrument.cash.USDollar;

public final class InstrumentFactory {
    public static Instrument getInstrument(final InvestType type, final Double amount) {
        switch (type) {
            case BOND:
                if (amount % 1 != 0) {
                    throw new WrongInstrumentAmountException();
                }
                return new Bond(amount);
            case STOCK:
                if (amount % 1 != 0) {
                    throw new WrongInstrumentAmountException();
                }
                return new Stock(amount);
            case CASH_EUR:
                return new Euro(amount);
            case CASH_USD:
                return new USDollar(amount);
            default:
                throw new SystemException(1, "Unexpected");
        }
    }
}
