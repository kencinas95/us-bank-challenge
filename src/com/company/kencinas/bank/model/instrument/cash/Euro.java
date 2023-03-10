package com.company.kencinas.bank.model.instrument.cash;

import com.company.kencinas.bank.model.instrument.Cash;
import com.company.kencinas.bank.util.cash.Currency;
import com.company.kencinas.bank.util.instrument.InvestType;

public class Euro implements Cash {
    private Double amount;

    public Euro(Double amount) {
        this.amount = amount;
    }

    @Override
    public Currency getCurrency() {
        return Currency.EURO;
    }

    @Override
    public InvestType getType() {
        return InvestType.CASH_EUR;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }
}
