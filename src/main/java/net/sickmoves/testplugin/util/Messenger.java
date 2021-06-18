package net.sickmoves.testplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Messenger {
    private final Plugin plugin;

    public Messenger(Plugin plugin) {
        this.plugin = plugin;
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(message);
    }
}
