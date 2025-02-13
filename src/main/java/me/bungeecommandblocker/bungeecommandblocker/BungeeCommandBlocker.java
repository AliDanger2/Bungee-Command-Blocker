package me.bungeecommandblocker.bungeecommandblocker;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeCommandBlocker extends Plugin implements Listener {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        getProxy().getPluginManager().registerListener(this, this);

        getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));

        getLogger().info(ChatColor.translateAlternateColorCodes('&',"\n" +
                "            &a.----------------.  .----------------.  .----------------. \n" +
                "           &a| .--------------. || .--------------. || .--------------. |\n" +
                "           &a| |   &b______&a     | || |     &b______&a   | || |   &b______&a     | |\n" +
                "           &a| |  &b|_   _ \\&a    | || |   &b.' ___  |&a  | || |  &b|_   _ \\&a    | |\n" +
                "           &a| |    &b| |_) |&a   | || |  &b/ .'   \\_|&a  | || |    &b| |_) |&a   | |\n" +
                "           &a| |    &b|  __'.&a   | || |  &b| |&a         | || |    &b|  __'.&a   | |\n" +
                "           &a| |   &b_| |__) |&a  | || |  &b\\ `.___.'\\&a  | || |   &b_| |__) |&a  | |\n" +
                "           &a| |  &b|_______/&a   | || |   &b`._____.'&a  | || |  &b|_______/&a   | |\n" +
                "           &a| |              | || |              | || |              | |\n" +
                "           &a| '--------------' || '--------------' || '--------------' |\n" +
                "            &a'----------------'  '----------------'  '----------------' \n" +
                "                      &a╔════════════════════════════════════╗\n" +
                "                      &a║    &r&bBungeeCommandBlocker &aEnabled    ║\n" +
                "                      &a║           &bVersion &r: [&a1.2&r]          &a║\n" +
                "                      &a║            &bBy &6Ali_Danger           &a║\n" +
                "                      &a╚════════════════════════════════════╝\n"));

        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&eBungeeCommandBlocker &aenabled!"));
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"\n" +
                "            &a.----------------.  .----------------.  .----------------. \n" +
                "           &a| .--------------. || .--------------. || .--------------. |\n" +
                "           &a| |   &b______&a     | || |     &b______&a   | || |   &b______&a     | |\n" +
                "           &a| |  &b|_   _ \\&a    | || |   &b.' ___  |&a  | || |  &b|_   _ \\&a    | |\n" +
                "           &a| |    &b| |_) |&a   | || |  &b/ .'   \\_|&a  | || |    &b| |_) |&a   | |\n" +
                "           &a| |    &b|  __'.&a   | || |  &b| |&a         | || |    &b|  __'.&a   | |\n" +
                "           &a| |   &b_| |__) |&a  | || |  &b\\ `.___.'\\&a  | || |   &b_| |__) |&a  | |\n" +
                "           &a| |  &b|_______/&a   | || |   &b`._____.'&a  | || |  &b|_______/&a   | |\n" +
                "           &a| |              | || |              | || |              | |\n" +
                "           &a| '--------------' || '--------------' || '--------------' |\n" +
                "            &a'----------------'  '----------------'  '----------------' \n" +
                "                      &a╔════════════════════════════════════╗\n" +
                "                      &a║   &r&bBungeeCommandBlocker &aDisabled!   ║\n" +
                "                      &a║           &bVersion &r: [&a1.2&r]          &a║\n" +
                "                      &a║            &bBy &6Ali_Danger           &a║\n" +
                "                      &a╚════════════════════════════════════╝\n"));
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&eBungeeCommandBlocker &cdisabled!"));
    }

    @EventHandler
    public void onCommand(ChatEvent event) {
        if (!(event.getSender() instanceof ProxiedPlayer) || !event.isCommand()) {
            return;
        }

        ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
        String[] args = event.getMessage().substring(1).split(" "); // Remove "/"
        if (args.length == 0) return;

        String command = args[0]; // The main command
        List<String> blockedServers = configManager.getBlockedServers();
        List<String> blockedCommands = configManager.getBlockedCommands();
        String errorMessage = ChatColor.translateAlternateColorCodes('&', configManager.getErrorMessage());

        // Handle `/send all (server)`
        if (command.equalsIgnoreCase("send") && args.length > 1 && args[1].equalsIgnoreCase("all")) {
            if (args.length < 3) return; // Prevent errors if no target server is specified
            String targetServer = args[2];

            List<ProxiedPlayer> allPlayers = getProxy().getPlayers().stream().collect(Collectors.toList());
            List<ProxiedPlayer> blockedPlayers = allPlayers.stream()
                    .filter(player -> blockedServers.contains(player.getServer().getInfo().getName()))
                    .collect(Collectors.toList());

            int blockedCount = blockedPlayers.size();
            int allowedCount = allPlayers.size() - blockedCount;

            // Cancel the event to prevent the default behavior
            event.setCancelled(true);

            // Send only allowed players
            for (ProxiedPlayer player : allPlayers) {
                if (!blockedPlayers.contains(player)) {
                    player.connect(getProxy().getServerInfo(targetServer));
                }
            }

            // Build the proper message
            String message;
            if (allowedCount == 0) {
                message = configManager.getNoPlayersMovedMessage();
            } else if (blockedCount == 0) {
                message = configManager.getAllPlayersMovedMessage()
                        .replace("{AMOUNT_OF_PLAYERS}", String.valueOf(allowedCount))
                        .replace("{SERVER}", targetServer)
                        .replace("{PLAYER_TEXT}", (allowedCount == 1 ? "player" : "players"));
            } else {
                message = configManager.getPartialPlayersMovedMessage()
                        .replace("{AMOUNT_OF_PLAYERS}", String.valueOf(allowedCount))
                        .replace("{SERVER}", targetServer)
                        .replace("{PLAYER_TEXT}", (allowedCount == 1 ? "player" : "players"))
                        .replace("{AMOUNT_OF_PLAYERS_IN_BLOCKED_SERVER}", String.valueOf(blockedCount))
                        .replace("{EXCEPTION_PLAYER_TEXT}", (blockedCount == 1 ? "player" : "players"));
            }

            // Send formatted message to sender
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return;
        }

        // Block all other commands if they target a player in a blocked server
        if (blockedCommands.contains(command) && args.length > 1) {
            ProxiedPlayer target = getProxy().getPlayer(args[1]);

            if (target != null && blockedServers.contains(target.getServer().getInfo().getName())) {
                sender.sendMessage(errorMessage);
                event.setCancelled(true);
            }
        }
    }

    public void reloadConfig() {
        configManager.loadConfig();
    }
}
