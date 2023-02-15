package com.jpmorgan.kencinas.bank.model.instrument.cash;

import com.jpmorgan.kencinas.bank.model.instrument.Cash;
import com.jpmorgan.kencinas.bank.util.cash.Currency;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;

public class USDollar implements Cash {
    private Double amount;

    public USDollar(Double amount) {
        this.amount = amount;
    }

    @Override
    public Currency getCurrency() {
        return Currency.US_DOLLAR;
    }

    @Override
    public InvestType getType() {
        return InvestType.CASH_USD;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }
}
