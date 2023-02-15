package com.jpmorgan.kencinas.bank.model;

import com.jpmorgan.kencinas.bank.util.cash.Currency;

public class Price {
    private Currency currency;

    private Double amount;

    public Price() {}

    public Price(Currency currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }
}
