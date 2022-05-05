package org.melo.discordbot.utils.commandConfig.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
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

        event.getChannel().sendMessage("Is the music you're asking me to play \""+musicName+"\"?");


        ServerVoiceChannel channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
        User user = event.getMessageAuthor().asUser().get();
        event.getChannel().sendMessage("Joining channel : "+channel.getName());

        channel.connect(false, true).thenAccept(audioConnection -> {
            event.getChannel().sendMessage("I am successfully connected to your channel, "+user.getName());
            channel.disconnect();
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
