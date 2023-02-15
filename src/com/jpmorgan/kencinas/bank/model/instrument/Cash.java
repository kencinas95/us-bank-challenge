package com.jpmorgan.kencinas.bank.model.instrument;

import com.jpmorgan.kencinas.bank.model.Instrument;
import com.jpmorgan.kencinas.bank.util.cash.Currency;


public interface Cash extends Instrument {
    Currency getCurrency();
}
