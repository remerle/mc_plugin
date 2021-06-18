package net.sickmoves.testplugin;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for the plugin
 */
public final class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
        new net.sickmoves.testplugin.lavafeet.Feature(this).register();
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
        HandlerList.unregisterAll(this);
    }
}
