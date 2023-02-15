package com.company.kencinas.bank.model;

import com.company.kencinas.bank.util.instrument.InvestType;

public interface Instrument {
    InvestType getType();

    Double getAmount();
}
