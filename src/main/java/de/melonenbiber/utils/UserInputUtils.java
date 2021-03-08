package de.melonenbiber.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserInputUtils
{

    private static final List<String> validCurrencies = Arrays.asList("aed", "ars", "aud", "bch", "bdt", "bhd", "bmd", "bnb", "brl", "btc", "cad", "chf", "clp", "cny", "czk", "dkk", "dot", "eos", "eth", "eur", "gbp", "hkd", "huf", "idr", "ils", "inr", "jpy", "krw", "kwd", "lkr", "ltc", "mmk", "mxn", "myr", "ngn", "nok", "nzd", "php", "pkr", "pln", "rub", "sar", "sek", "sgd", "thb", "try", "twd", "uah", "usd", "vef", "vnd", "xag", "xau", "xdr", "xlm", "xrp", "yfi", "zar", "bits", "link", "sats");


    public static Optional<String> convertCryptoCurrencyInputToId(String input)
    {
        for (SimpleCryptocurrency simpleCryptocurrency : CoingeckoApiUtils.getAllSimpleCryptocurrencies())
        {
            if (simpleCryptocurrency.id.equalsIgnoreCase(input))
                return Optional.of(input);

            if (simpleCryptocurrency.symbol.equalsIgnoreCase(input))
                return Optional.of(simpleCryptocurrency.id);

        }
        return Optional.empty();
    }

    public static boolean isCurrencyValid(String currencyName)
    {
        return validCurrencies.contains(currencyName);
    }
}
