package de.melonenbiber.commands;

import de.melonenbiber.PolitikclubCryptoBot;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Optional;

public class CommandHelp extends CommandBase
{
    public CommandHelp(String name, String[] aliases, int minArgs, int maxArgs)
    {
        super(name, aliases, minArgs, maxArgs);
        helpMessage = "!help [command]";
    }

    @Override
    public boolean execute(String[] args, User author, MessageChannel channel)
    {
        if (args.length == 0)
        {
            StringBuilder builder = new StringBuilder();
            for (CommandBase command : PolitikclubCryptoBot.commandManager.commands)
            {
                builder.append(command.name).append("\n");
            }
            channel.sendMessage("**Available commands:**\n" + builder.toString()).queue();
        } else if (args.length == 1)
        {
            Optional<CommandBase> command = PolitikclubCryptoBot.commandManager.getCommandByAlias(args[0]);

            if (command.isPresent())
                channel.sendMessage(command.get().helpMessage).queue();
            else
                channel.sendMessage("Could not find command '" + args[0] + "'.\nType !help to see a list of available commands!").queue();
        }
        return true;
    }
}
