package org.melo.discordbot.utils.commandConfig.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.utils.commandConfig.Command;
import org.melo.discordbot.utils.commandConfig.CommandExecutor;

public class CommandPing implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        event.getChannel().sendMessage("Pong");
    }
}
