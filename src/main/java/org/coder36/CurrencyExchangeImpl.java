package org.coder36;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CurrencyExchangeImpl implements CurrencyExchange {

    private Map<String, ExchangeRate> exchangeRates = new HashMap<>();

    public CurrencyExchangeImpl(String formattedRates ) {
        Arrays.stream(formattedRates.split(System.lineSeparator())).map(m -> m.split(",")).forEach(row -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.country = row[0].trim();
            exchangeRate.currency = row[1].trim();
            exchangeRate.currencyCode = row[2].trim();
            exchangeRate.rate = new BigDecimal(row[3].trim());
            exchangeRates.put(exchangeRate.currencyCode, exchangeRate);
        });
    }

    public String convert( String amount, String srcCurrencyCode, String targetCurrencyCode ) {
        // use BigDecimal as they allow for a arbitrarily large precision
        BigDecimal val = new BigDecimal(amount);
        ExchangeRate src = exchangeRates.get( srcCurrencyCode.toUpperCase() );
        ExchangeRate target = exchangeRates.get( targetCurrencyCode.toUpperCase() );

        // (amount / src.rate) * target.rate
        BigDecimal conversion = val.divide( src.rate, 20, RoundingMode.UP  ).multiply( target.rate );
        return  conversion.setScale(2, RoundingMode.UP) + " " + target.country + " " + target.currency;
    }

    public boolean hasCurrencyCode(String code ) {
        return exchangeRates.containsKey(code.toUpperCase());
    }

    private class ExchangeRate {
        public String country;      // eg. Austrailia
        public String currency;     // eg, Dollars
        public String currencyCode; // eg. AUD
        public BigDecimal rate;     // eg. 1.51239
    }
}


