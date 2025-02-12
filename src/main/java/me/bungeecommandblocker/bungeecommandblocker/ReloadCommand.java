package me.bungeecommandblocker.bungeecommandblocker;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;

public class ReloadCommand extends Command {

    private final BungeeCommandBlocker plugin;

    public ReloadCommand(BungeeCommandBlocker plugin) {
        super("bcb", "bcb.reload", "bungeeblockreload");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBungeeCommandBlocker Configuration Reloaded!"));
    }
}