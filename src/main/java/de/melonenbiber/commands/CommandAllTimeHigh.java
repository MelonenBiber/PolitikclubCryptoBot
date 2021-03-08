package de.melonenbiber.commands;

import de.melonenbiber.utils.ApiCallException;
import de.melonenbiber.utils.CoingeckoApiUtils;
import de.melonenbiber.utils.Cryptocurrency;
import de.melonenbiber.utils.UserInputUtils;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Optional;

public class CommandAllTimeHigh extends CommandBase
{
    public CommandAllTimeHigh(String name, String[] aliases, int minArgs, int maxArgs)
    {
        super(name, aliases, minArgs, maxArgs);
        helpMessage = "!(alltimehigh|ath) <coin> [currency]\nExample: !ath btc eur";
    }

    @Override
    public boolean execute(String[] args, User author, MessageChannel channel)
    {
        String cryptoCurrencyName = args[0];
        String currencyName = args.length > 1 ? args[1] : "usd";

        // Validate Cryptocurrencyname
        Optional<String> optionalCryptoCurrencyName = UserInputUtils.convertCryptoCurrencyInputToId(cryptoCurrencyName);

        if (optionalCryptoCurrencyName.isEmpty())
        {
            channel.sendMessage("Unknown cryptocurrency '" + cryptoCurrencyName + "'").queue();
            return true;
        }

        String cryptoCurrencyId = optionalCryptoCurrencyName.get();

        // Validate Currencyname
        if (!UserInputUtils.isCurrencyValid(currencyName))
        {
            channel.sendMessage("Unknown currency '" + currencyName + "'").queue();
            return true;
        }


        try
        {
            Cryptocurrency c = CoingeckoApiUtils.getCryptocurrency(cryptoCurrencyId);
            channel.sendMessage("Ath for **" + c.name + "** is **"+ c.market_data.ath.get(currencyName) + " " + currencyName + "**").queue();

            return true;
        } catch (ApiCallException e)
        {
            channel.sendMessage(e.getMessage()).queue();
            return true;
        }
    }
}
