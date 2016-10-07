package org.coder36;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Main application entry point.
 * Loads rates from ./exchange_rates.txt
 */
public class Cli {

    public static void main(String [] args ){
        try {
            String formattedExchangeRates = new String(Files.readAllBytes(Paths.get("./exchange_rates.txt")));
            Cli cli = new Cli(formattedExchangeRates);
            cli.start();
        }
        catch(Exception e) {
            System.err.println("Error:  Check format of ./exchange_rates.txt");
        }
    }


    private CurrencyExchange exchange;

    private Cli(String formattedExchangeRates ) {;
        exchange = new CurrencyExchangeImpl(formattedExchangeRates);
    }

    private void start() {
        String amount =  ask("Amount: ", "Must be a number", (inp) -> inp.matches("^[0-9]\\d*(\\.\\d+)?$"));
        String srcCode = ask("Source currency code: ", "Code not found", (inp) -> exchange.hasCurrencyCode(inp));
        String targetCode = ask("Target currency code: ", "Code not found", (inp) -> exchange.hasCurrencyCode(inp));
        System.out.println(exchange.convert(amount, srcCode, targetCode));
    }

    private String ask(String question, String invalidResponse, Function<String, Boolean> fn) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        String inp = "";
        while( !(  fn.apply(inp = scanner.nextLine()) )  ) {
            System.out.println(invalidResponse);
            System.out.print(question);
        }
        return inp;
    }
}
