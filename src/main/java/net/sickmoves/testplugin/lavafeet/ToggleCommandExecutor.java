package net.sickmoves.testplugin.lavafeet;

import net.sickmoves.testplugin.common.Constants;
import net.sickmoves.testplugin.util.Messenger;
import net.sickmoves.testplugin.util.MetadataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ToggleCommandExecutor implements CommandExecutor {
    private final Plugin plugin;
    private final MetadataManager metadata;
    private final Messenger messenger;

    public ToggleCommandExecutor(Plugin plugin) {
        this.plugin = plugin;
        this.metadata = new MetadataManager(plugin);
        this.messenger = new Messenger(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player target = null;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
        } else if (sender instanceof Player) {
            target = (Player) sender;
        }
        if (target == null) {
            return false;
        }

        // Toggle lava feet
        boolean lavaFeetEnabled = metadata.getBoolean(target, Constants.Metadata.LAVA_FEET_ENABLED, false);
        if (lavaFeetEnabled) {
            messenger.broadcast(ChatColor.YELLOW + "[" + target.getDisplayName() + "]" + ChatColor.RESET + " no longer has lava feet :(");
            metadata.setValue(target, Constants.Metadata.LAVA_FEET_ENABLED, false);
        } else {
            messenger.broadcast(ChatColor.YELLOW + "[" + target.getDisplayName() + "]" + ChatColor.RESET + " now has " + ChatColor.RED + " LAVA FEET!");
            metadata.setValue(target, Constants.Metadata.LAVA_FEET_ENABLED, true);
        }
        return true;
    }
}
