package com.company.kencinas.bank.model.instrument;

import com.company.kencinas.bank.model.Instrument;
import com.company.kencinas.bank.util.instrument.InvestType;

public class Bond implements Instrument {
    private Double amount;

    public Bond(Double amount) {
        this.amount = amount;
    }

    @Override
    public InvestType getType() {
        return InvestType.BOND;
    }

    @Override
    public Double getAmount() {
        return amount;
    }
}
