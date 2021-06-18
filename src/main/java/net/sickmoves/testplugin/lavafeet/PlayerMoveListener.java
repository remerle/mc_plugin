package net.sickmoves.testplugin.lavafeet;

import net.sickmoves.testplugin.common.Constants;
import net.sickmoves.testplugin.util.MetadataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class PlayerMoveListener implements Listener {
    public static final long TICKS_PER_SECOND = 20L;
    private final Plugin plugin;
    private final MetadataManager metadata;

    public PlayerMoveListener(Plugin plugin) {
        this.plugin = plugin;
        this.metadata = new MetadataManager(plugin);
    }

    @EventHandler
    public void playerMoveHandler(PlayerMoveEvent event) {
        if (!metadata.getBoolean(event.getPlayer(), Constants.Metadata.LAVA_FEET_ENABLED, false)) {
            return;
        }
        Location location = event.getFrom();

        // Since the player location is at their feet, we need to get one block down to get where
        // they are standing
        final Block fromBlock = location.getBlock().getRelative(BlockFace.DOWN);
        final Player player = event.getPlayer();

        // Players can move fractions of a block, so keep track to see if we've actually moved to a new block
        Optional<Location> lastLocation = this.metadata.getLocation(player, "last-block");
        if (lastLocation.isPresent()) {
            if (fromBlock.getLocation().equals(lastLocation.get())) {
                // We haven't moved blocks
                return;
            }
        }
        this.metadata.setValue(player, "last-block", fromBlock.getLocation());

        // We don't want to convert AIR to LAVA, and if we're already LAVA, there's nothing to do
        if (fromBlock.getType() != Material.AIR && fromBlock.getType() != Material.LAVA) {
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                Block playerBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

                // Try to avoid drowning the player in lava
                if (playerBlock.getX() != fromBlock.getX() || playerBlock.getY() != fromBlock.getY() || playerBlock.getZ() != fromBlock.getZ()) {
                    final Material origMaterial = fromBlock.getType();
                    fromBlock.setType(Material.LAVA);

                    // Revert the block
                    Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                        fromBlock.setType(origMaterial);
                    }, 30 * TICKS_PER_SECOND);
                }
            }, 1 * TICKS_PER_SECOND);
        }
    }
}
