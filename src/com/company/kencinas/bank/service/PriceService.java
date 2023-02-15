package com.company.kencinas.bank.service;

import com.company.kencinas.bank.model.Price;
import com.company.kencinas.bank.util.cash.Currency;
import com.company.kencinas.bank.util.instrument.InvestType;

public interface PriceService {
    Price getPriceForInstrumentByCurrency(Currency currency, InvestType type);

}
