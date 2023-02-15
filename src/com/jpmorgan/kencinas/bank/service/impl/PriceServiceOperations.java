package com.jpmorgan.kencinas.bank.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.kencinas.bank.model.Price;
import com.jpmorgan.kencinas.bank.service.PriceService;
import com.jpmorgan.kencinas.bank.util.cash.Currency;
import com.jpmorgan.kencinas.bank.util.cash.CurrencyConverter;
import com.jpmorgan.kencinas.bank.util.instrument.InvestType;
import com.jpmorgan.kencinas.bank.util.instrument.PricingExternalService;

public class PriceServiceOperations implements PriceService {
    private static final Map<InvestType, Currency> INSTRUMENT_TYPE_CURRENCY_MAP;

    static {
        INSTRUMENT_TYPE_CURRENCY_MAP = new HashMap<>();
        INSTRUMENT_TYPE_CURRENCY_MAP.put(InvestType.CASH_EUR, Currency.EURO);
        INSTRUMENT_TYPE_CURRENCY_MAP.put(InvestType.CASH_USD, Currency.US_DOLLAR);
    }

    @Override
    public Price getPriceForInstrumentByCurrency(final Currency currency, final InvestType type) {
        Price price = new Price(currency, 0.0);

        switch (type) {
            case BOND:
            case STOCK:
                price.setAmount(PricingExternalService.getPriceForInstrumentByCurrency(currency, type));
                break;
            case CASH_USD:
            case CASH_EUR:
                price.setAmount(getAmountFromCashInstrument(currency, type));
                break;
            default:
                break;
        }

        // Assures a 2-decimals number more friendly
        price.setAmount(roundTo2Decimals(price.getAmount()));

        return price;
    }

    private Double getAmountFromCashInstrument(Currency currency, InvestType type) {
        Currency currentCurrency = INSTRUMENT_TYPE_CURRENCY_MAP.get(type);
        return currency.equals(currentCurrency) ? 1.0 : CurrencyConverter.convert(currentCurrency, currency, 1.0);
    }

    private Double roundTo2Decimals(final Double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
