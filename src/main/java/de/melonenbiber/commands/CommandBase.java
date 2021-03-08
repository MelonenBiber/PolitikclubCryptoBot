package de.melonenbiber.commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommandBase
{
    public static final String ACTIVATOR = "!";

    public String helpMessage;
    public String name;
    public List<String> aliases;
    public int minArgs;
    public int maxArgs;

    public CommandBase(String name, String[] aliases, int minArgs, int maxArgs)
    {
        this.name = name;
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
        this.aliases.add(name);
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
    }

    public abstract boolean execute(String[] args, User author, MessageChannel channel);
}
