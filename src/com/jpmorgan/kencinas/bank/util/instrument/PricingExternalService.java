package com.jpmorgan.kencinas.bank.util.instrument;

import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.kencinas.bank.util.cash.Currency;

public final class PricingExternalService {
    public static final Double USD_BOND_PRICE = 35.47;
    public static final Double USD_STOCK_PRICE = 132.50;
    public static final Double EUR_BOND_PRICE = 28.56;
    public static final Double EUR_STOCK_PRICE = 119.42;


    private static final Map<Currency, Map<InvestType, Double>> PRICE_TABLE;
    static {
        PRICE_TABLE = new HashMap<>();

        Map<InvestType, Double> usdPriceTable = new HashMap<>();
        usdPriceTable.put(InvestType.BOND, USD_BOND_PRICE);
        usdPriceTable.put(InvestType.STOCK, USD_STOCK_PRICE);

        Map<InvestType, Double> eurPriceTable = new HashMap<>();
        eurPriceTable.put(InvestType.BOND, EUR_BOND_PRICE);
        eurPriceTable.put(InvestType.STOCK, EUR_STOCK_PRICE);

        PRICE_TABLE.put(Currency.US_DOLLAR, usdPriceTable);
        PRICE_TABLE.put(Currency.EURO, eurPriceTable);

    }

    public static Double getPriceForInstrumentByCurrency(final Currency currency, final InvestType type) {
        // Mocks a third party pricing service
        return PRICE_TABLE.get(currency).get(type);
    }

}
