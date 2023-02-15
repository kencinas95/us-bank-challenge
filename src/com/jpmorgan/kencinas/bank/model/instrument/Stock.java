package com.jpmorgan.kencinas.bank.model.instrument;

import com.jpmorgan.kencinas.bank.model.Instrument;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;

public class Stock implements Instrument {
    private Double amount;

    public Stock(Double amount) {
        this.amount = amount;
    }

    @Override
    public InvestType getType() {
        return InvestType.STOCK;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }
}
