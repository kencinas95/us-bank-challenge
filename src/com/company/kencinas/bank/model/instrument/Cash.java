package com.company.kencinas.bank.model.instrument;

import com.company.kencinas.bank.model.Instrument;
import com.company.kencinas.bank.util.cash.Currency;

public interface Cash extends Instrument {
    Currency getCurrency();
}
