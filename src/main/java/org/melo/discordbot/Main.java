package org.melo.discordbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.melo.discordbot.utils.ConfigManager;
import org.melo.discordbot.commandConfig.MessageManager;
import java.io.File;

public class Main {
    private static DiscordApi api;
    private static ConfigManager configManager;

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static void main(String[] args) {
        configManager = new ConfigManager(new File(System.getProperty("user.dir"), "config.toml"));

        api = new DiscordApiBuilder().setToken(configManager.getToml().getString("bot.token")).login().join();

        api.addMessageCreateListener(MessageManager::create);
    }
}
