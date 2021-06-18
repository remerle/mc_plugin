package net.sickmoves.testplugin.lavafeet;

import net.sickmoves.testplugin.common.Constants;
import net.sickmoves.testplugin.common.FeatureProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Feature implements FeatureProvider {
    private final JavaPlugin plugin;

    public Feature(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register() {
        // Register event listeners
        plugin.getServer().getPluginManager().registerEvents(new PlayerMoveListener(plugin), plugin);

        // Register commands
        plugin.getCommand(Constants.Commands.LAVA_FEET).setExecutor(new ToggleCommandExecutor(plugin));
    }
}
