package com.company.kencinas.bank.model.instrument.cash;

import com.company.kencinas.bank.model.instrument.Cash;
import com.company.kencinas.bank.util.cash.Currency;
import com.company.kencinas.bank.util.instrument.InvestType;

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
