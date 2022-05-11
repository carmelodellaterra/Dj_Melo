package org.melo.discordbot.commandConfig.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.melo.discordbot.commandConfig.Command;
import org.melo.discordbot.commandConfig.CommandExecutor;
import org.melo.discordbot.resources.LavaplayerAudioSource;

public class CommandPlay implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {

        String musicName = getMusicName(args);
        event.getChannel().sendMessage("Is the music you're asking me to play \""+musicName+"\"?");


        ServerVoiceChannel channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
        User user = event.getMessageAuthor().asUser().get();
        event.getChannel().sendMessage("Joining channel : "+channel.getName());

        channel.connect(false, true).thenAccept(audioConnection -> {
            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            playerManager.registerSourceManager(new YoutubeAudioSourceManager());
            AudioPlayer player = playerManager.createPlayer();



            AudioSource source = new LavaplayerAudioSource(channel.getApi(), player);
            audioConnection.setAudioSource(source);

            playerManager.loadItem("https://www.youtube.com/watch?v=v1K4EAXe2oo", new AudioLoadResultHandler() {
                @Override
                public void trackLoaded(AudioTrack track) {
                    audioConnection.setAudioSource(source);
                    event.getChannel().sendMessage(audioConnection.getAudioSource().toString());
                    player.playTrack(track);
                    event.getChannel().sendMessage(audioConnection.getAudioSource().toString());

                }

                @Override
                public void playlistLoaded(AudioPlaylist playlist) {
                    for(AudioTrack track : playlist.getTracks()) {
                        player.playTrack(track);
                    }
                }

                @Override
                public void noMatches() {
                    event.getChannel().sendMessage("Stop pranking me and get me a real song!");
                }

                @Override
                public void loadFailed(FriendlyException exception) {
                    event.getChannel().sendMessage("Something went wrong");
                }
            });


        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

    public String getMusicName(String[] args){
        String musicName = "";
        for (String element : args) {
            musicName += element + " ";
        } musicName.substring(0, musicName.length()-1);
        return musicName;
    }
}
