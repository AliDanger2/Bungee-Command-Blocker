package me.bungeecommandblocker.bungeecommandblocker;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ConfigManager {
    private final BungeeCommandBlocker plugin;
    private Configuration config;

    public ConfigManager(BungeeCommandBlocker plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
                plugin.getDataFolder().mkdirs();
                Files.copy(plugin.getResourceAsStream("config.yml"), file.toPath());
            } catch (IOException e) {
                plugin.getLogger().severe("&cCould not create config file!");
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            plugin.getLogger().severe("&cCould not load config file!");
        }
    }

    public List<String> getBlockedServers() {
        return config.getStringList("blocked-servers");
    }

    public List<String> getBlockedCommands() {
        return config.getStringList("blocked-commands");
    }

    public String getErrorMessage() {
        return config.getString("error-message", "&cYou cannot use this command on a player in a blocked server!");
    }

    public String getAllPlayersMessage() {
        return config.getString("all-players-message",
                "&aSent {AMOUNT_OF_PLAYERS} players to {SERVER}, &eExcept {AMOUNT_OF_PLAYERS_IN_BLOCKED_SERVER} players which were in a blocked server.");
    }
}