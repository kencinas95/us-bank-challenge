package com.jpmorgan.kencinas.bank.service;

import com.jpmorgan.kencinas.bank.model.Price;
import com.jpmorgan.kencinas.bank.util.cash.Currency;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;

public interface PriceService {
    Price getPriceForInstrumentByCurrency(Currency currency, InvestType type);

}
