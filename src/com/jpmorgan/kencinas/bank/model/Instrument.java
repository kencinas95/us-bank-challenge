package com.jpmorgan.kencinas.bank.model;

import com.jpmorgan.kencinas.bank.util.instrument.InvestType;

public interface Instrument {
    InvestType getType();

    Double getAmount();
}
