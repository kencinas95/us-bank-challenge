package com.jpmorgan.kencinas.bank.model;

import com.jpmorgan.kencinas.bank.util.transaction.Action;
import com.jpmorgan.kencinas.bank.util.transaction.TransactionDate;

public class Transaction {
    private TransactionDate date;

    private Instrument instrument;

    private Action action;

    private Price price;

    public TransactionDate getDate() {
        return date;
    }

    public void setDate(final TransactionDate date) {
        this.date = date;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(final Instrument instrument) {
        this.instrument = instrument;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(final Price price) {
        this.price = price;
    }
}
