package org.melo.discordbot.commandConfig;

import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.Main;
import org.melo.discordbot.commandConfig.commands.CommandHelp;
import org.melo.discordbot.commandConfig.commands.CommandPing;
import org.melo.discordbot.commandConfig.commands.CommandPlay;

import java.util.Arrays;

public class MessageManager {
    private static CommandRegistry registry = new CommandRegistry();

    static{
        registry.addCommand(new Command(
                "ping",
                "Pings the bot",
                new CommandPing(),
                "ping"
        ));

        registry.addCommand((new Command(
                "help",
                "Display every command and gives a quick explanation about it",
                new CommandHelp(),
                "help", "?", "aled"
        )));

        registry.addCommand((new Command(
                "play",
                "Search for the music given in argument and joins the voice channel to play it",
                new CommandPlay(),
                "play"
        )));
    }

    private static final String PREFIX = Main.getConfigManager().getToml().getString("bot.prefix");

    public static void create(MessageCreateEvent event){
        if(event.getMessageContent().startsWith(PREFIX)){
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(PREFIX.length());
            args = args.length==1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length);

            String[] finalArgs = args;
            registry.getByAlias(commandName).ifPresent((cmd) ->{
                cmd.getExecutor().run(event, cmd, finalArgs);
            });
        }
    }
}
