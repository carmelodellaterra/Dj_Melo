package org.melo.discordbot.utils.commandConfig.commands;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.utils.commandConfig.Command;
import org.melo.discordbot.utils.commandConfig.CommandExecutor;

public class CommandPlay implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        String musicName = "";
        for (String element : args) {
            musicName += element + " ";
        } musicName.substring(0, musicName.length()-1);

        event.getChannel().sendMessage("Is the music you're asking me to play "+musicName+"?");

        ServerVoiceChannel channel;


    }
}
