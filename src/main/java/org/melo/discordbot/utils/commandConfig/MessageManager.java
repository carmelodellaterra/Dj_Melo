package org.melo.discordbot.utils.commandConfig;

import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.Main;
import org.melo.discordbot.utils.commandConfig.commands.CommandPing;
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
    }

    private static final String PREFIX = Main.getConfigManager().getToml().getString("bot.prefix");

    public static void create(MessageCreateEvent event){
        if(event.getMessageContent().startsWith(PREFIX)){
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(PREFIX.length());
            args = args.length==1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length-1);

            String[] finalArgs = args;
            registry.getByAlias(commandName).ifPresent((cmd) ->{
                cmd.getExecutor().run(event, cmd, finalArgs);
            });
        }
    }
}
