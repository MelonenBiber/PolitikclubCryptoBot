package de.melonenbiber.utils;

import java.util.Optional;

public class UserInputUtils
{

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
}
