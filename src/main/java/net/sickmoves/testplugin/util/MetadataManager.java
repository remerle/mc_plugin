package net.sickmoves.testplugin.util;

import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.Optional;

/**
 * Metadata manager for a plugin
 */
public class MetadataManager {
    private final Plugin plugin;

    public MetadataManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean hasKey(Metadatable object, String key) {
        return object.hasMetadata(key);
    }

    public boolean getBoolean(Metadatable object, String key, boolean defaultValue) {
        return getValue(object, key).orElse(new FixedMetadataValue(this.plugin, defaultValue))
                                    .asBoolean();
    }

    public Optional<Location> getLocation(Metadatable object, String key) {
        Optional<MetadataValue> value = getValue(object, key);
        return value.map(metadataValue -> (Location) Objects.requireNonNull(metadataValue.value()));
    }

    public void setValue(Metadatable object, String key, Object value) {
        object.setMetadata(key, new FixedMetadataValue(this.plugin, value));
    }

    public Optional<MetadataValue> getValue(Metadatable object, String key) {
        return object.getMetadata(key)
                     .stream()
                     .filter(x -> x.getOwningPlugin() == this.plugin)
                     .findFirst();
    }
}
