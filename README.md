# Currency-Converter

"Write a command line app to convert currencies"


## Build Instructions

Assuming JDK8 and Maven are installed:

```
mvn clean package
```


## Running

The maven build process creates an executable jar.  To run:

```
java -jar target/currencyconverter-1.0-SNAPSHOT-jar-with-dependencies.jar
```

You will then be prompted to provide an amount, "ISO4217 source currency" and "ISO4217 target currency".  The exchange rates are listed in the `exchange_rates.txt` file.

Example session:
```
[mark:~/dev/cash-converter]$ java -jar target/currencyconverter-1.0-SNAPSHOT-jar-with-dependencies.jar
Amount: 100.00
Source currency code: AUD
Target  currency code: BGN
172.55 Bulgaria Leva
```

The inputs are validated.  The Amount field must be number, and the currency codes must exist in the `exchange_rates.txt` file. 


## Rounding

Internally, the currency conversion uses BigDecimal to allow for arbitrarily large precision.  The conversion displayed back to the user
is rounded upwards, to 2 decimal places.

## The exchange_rates file

When the Converter starts, it looks in the current working directory for a file named `exchange_rates.txt`.  The format of the exchange_rates file is as follows:
```
United Arab Emirates, Dirhams, AED, 7.2104
Australia, Dollars, AUD, 1.51239
Bosnia and Herzegovina, Convertible Marka, BAM, 2.60565
Bulgaria, Leva, BGN, 2.60948
```

