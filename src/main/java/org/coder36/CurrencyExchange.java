package org.coder36;

public interface CurrencyExchange {

    /**
     * Converts amount from srcCurrencyCode to targetCurrencyCode
     * @return Conversion amount eg. "476.76 United Arab Emirates Dirhams"
     */
    String convert( String amount, String srcCurrencyCode, String targetCurrencyCode );

    /**
     * @return true, if has exchange rate for provided currencyCode
     */
    boolean hasCurrencyCode(String currencyCode );
}
