package de.melonenbiber;

import de.melonenbiber.commands.CommandCheckPrice;
import de.melonenbiber.commands.CommandHelp;
import de.melonenbiber.commands.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PolitikclubCryptoBot
{
    public static CommandManager commandManager = new CommandManager();
    public static JDA jda;

    public static void main(String[] args)
    {
        commandManager.addCommand(new CommandCheckPrice("price", new String[0], 1, 2));
        commandManager.addCommand(new CommandHelp("help", new String[0], 0, 1));

        try
        {
            String discordToken = Files.readAllLines(Path.of("discord_token")).get(0);
            System.out.print(discordToken);
            System.out.println(discordToken.length());
            jda = JDABuilder.createDefault(discordToken)
                    .addEventListeners(commandManager)
                    .build();
        } catch (LoginException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
