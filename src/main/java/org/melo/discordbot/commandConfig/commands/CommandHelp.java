package org.melo.discordbot.commandConfig.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.commandConfig.Command;
import org.melo.discordbot.commandConfig.CommandExecutor;

public class CommandHelp implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        event.getChannel().sendMessage("Hello, you can call me DJ Melo, because that's my name !\n" +
                " I actually am under development, but here is everything i can do !\n\n" +
                "**-> §help :** Gives explanation about every command \n" +
                "**-> §ping :** Test function, answer pong everytime ping is called \n" +
                "**-> §play** some_music : Search for the music given and joins the channel to play it **/!\\ Under development, Wont work**");
    }
}
