package com.jpmorgan.kencinas.bank.util.cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class CurrencyConverter {
    private static final Double EUR_TO_USD_RATIO = 0.87;

    private static final Map<Currency, Map<Currency, Double>> CONVERSION_RATIO_TABLE;
    static {
        CONVERSION_RATIO_TABLE = new HashMap<>();

        Map<Currency, Double> conversionTableEUR = new HashMap<>();
        conversionTableEUR.put(Currency.US_DOLLAR, 1/EUR_TO_USD_RATIO);
        conversionTableEUR.put(Currency.EURO, 1.0);

        Map<Currency, Double> conversionTableUSD = new HashMap<>();
        conversionTableUSD.put(Currency.EURO, EUR_TO_USD_RATIO);
        conversionTableUSD.put(Currency.US_DOLLAR, 1.0);

        CONVERSION_RATIO_TABLE.put(Currency.EURO, conversionTableEUR);
        CONVERSION_RATIO_TABLE.put(Currency.US_DOLLAR, conversionTableUSD);
    }

    public static Double convert(final Currency from, final Currency to, final Double amount) {
        // Mocks a third party currency converter
        return Optional.ofNullable(CONVERSION_RATIO_TABLE.get(from))
            .map(conversionTable -> conversionTable.get(to))
            .map(ratio -> amount * ratio)
            .orElse(amount);
    }
}
