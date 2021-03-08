package de.melonenbiber.commands;

import de.melonenbiber.PolitikclubCryptoBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandManager implements EventListener
{
    // String that marks the beginning of the command
    public List<CommandBase> commands = new ArrayList<>();

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent)
    {
        if (genericEvent instanceof MessageReceivedEvent)
        {
            User author = ((MessageReceivedEvent) genericEvent).getAuthor();

            // Is Message from the bot himself
            if (author.getId().equals(PolitikclubCryptoBot.jda.getSelfUser().getId()))
                return;

            Message message = ((MessageReceivedEvent) genericEvent).getMessage();
            MessageChannel channel = ((MessageReceivedEvent) genericEvent).getChannel();
            String messageContentRaw = message.getContentRaw();

            String[] messageContentArray = messageContentRaw.split(" ");

            if (!StringUtils.startsWith(messageContentArray[0], CommandBase.ACTIVATOR))
                return;

            String commandName = messageContentArray[0].replaceFirst(CommandBase.ACTIVATOR, "");
            String[] args = new String[messageContentArray.length - 1];

            // Copy Text from Message as array, except first element/word
            // !price bitcoin usd --> '!price' wont be copied
            System.arraycopy(messageContentArray, 1, args, 0, args.length);
            // Convert all elements to lowercase
            args = Arrays.stream(args).map(String::toLowerCase).toArray(String[]::new);

            for (CommandBase command : commands)
                for (String alias : command.aliases)
                    if (alias.equalsIgnoreCase(commandName))
                        // Check for arg length so you don't have to in every call of every command.execute()
                        if ((args.length < command.minArgs || args.length > command.maxArgs) || !command.execute(args, author, channel))
                            channel.sendMessage("Try '!help " + commandName + "'").queue();
        }
    }

    public void addCommand(CommandBase command)
    {
        commands.add(command);
    }

    public Optional<CommandBase> getCommandByAlias(String alias)
    {
        for (CommandBase command : commands)
            for (String currentAlias : command.aliases)
                if (currentAlias.equalsIgnoreCase(alias))
                    return Optional.of(command);
        return Optional.empty();
    }
}
