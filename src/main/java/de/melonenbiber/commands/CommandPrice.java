package de.melonenbiber.commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import de.melonenbiber.utils.ApiCallException;
import de.melonenbiber.utils.CoingeckoApiUtils;
import de.melonenbiber.utils.Cryptocurrency;

import java.util.Random;

public class CommandPrice extends CommandBase
{
    public CommandPrice(String name, String[] aliases, int minArgs, int maxArgs)
    {
        super(name, aliases, minArgs, maxArgs);
        helpMessage = "!price <coin> [currency]\nExample: !price bitcoin usd";
    }

    @Override
    public boolean execute(String[] args, User author, MessageChannel channel)
    {
        String currency = args.length > 1 ? args[1] : "usd";

        try
        {
            Cryptocurrency c = CoingeckoApiUtils.getCryptocurrency(args[0]);

            double price;
            try
            {
                price = c.market_data.current_price.get(currency);
            } catch (NullPointerException e)
            {
                channel.sendMessage("Currency '" + currency + "' not found.").queue();
                return true;
            }

            if (new Random().nextInt(20) % 20 == 0 && author.getId().equalsIgnoreCase("205440355452190720"))
            {
                channel.sendMessage("Elena du bist deppert!").queue();
            }

            channel.sendMessage(price + " " + currency).queue();

        } catch (ApiCallException e)
        {
            channel.sendMessage(e.getMessage()).queue();
        }
        return true;
    }
}
