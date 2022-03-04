package org.melo.discordbot.utils.commandConfig;

import org.javacord.api.event.message.MessageCreateEvent;

public interface CommandExecutor {
    void run(MessageCreateEvent event, Command command, String[] args);
}
