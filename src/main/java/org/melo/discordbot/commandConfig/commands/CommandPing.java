package org.melo.discordbot.commandConfig.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.commandConfig.Command;
import org.melo.discordbot.commandConfig.CommandExecutor;

public class CommandPing implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        event.getChannel().sendMessage("Pong");
    }
}
