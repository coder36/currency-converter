package org.coder36;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyExchangeTest {

    @Test
    public void it_reads_in_specially_formatted_exchange_rates() {
        String rates = "" +
                "United Arab Emirates, Dirhams, AED, 7.2104\n" +
                "Australia, Dollars, AUD, 1.51239\n" +
                "Bosnia and Herzegovina, Convertible Marka, BAM, 2.60565\n" +
                "Bulgaria, Leva, BGN, 2.60948\n";

        CurrencyExchangeImpl currencyExchange = new CurrencyExchangeImpl(rates);
        assertTrue(currencyExchange.hasCurrencyCode("AED"));
        assertTrue(currencyExchange.hasCurrencyCode("BGN"));
    }

    @Test
    public void it_allows_conversions_between_currencies() {
        String rates = "" +
                "United Arab Emirates, Dirhams, AED, 7.2104\n" +
                "Australia, Dollars, AUD, 1.51239";

        CurrencyExchangeImpl currencyExchange = new CurrencyExchangeImpl(rates);
        String res = currencyExchange.convert("100.00", "AUD", "AED");
        assertEquals( "476.76 United Arab Emirates Dirhams", res);
    }

    @Test
    public void it_rounds_conversions_upwards_to_2_decimal_places() {
        String rates =
                "United Arab Emirates, Dirhams, AED, 3.0\n" +
                "United Kingdom, Pounds, GBP, 1.00\n";

        CurrencyExchangeImpl currencyExchange = new CurrencyExchangeImpl(rates);
        assertEquals( "33.34 United Kingdom Pounds", currencyExchange.convert("100.00", "AED", "GBP"));
    }

    @Test
    public void converting_to_the_same_currency_may_result_in_lost_precision_due_to_rounding_upwards() {
        String rates = "United Arab Emirates, Dirhams, AED, 3.0\n";
        CurrencyExchangeImpl currencyExchange = new CurrencyExchangeImpl(rates);
        assertEquals( "100.01 United Arab Emirates Dirhams", currencyExchange.convert("100.00", "AED", "AED"));
    }

}
