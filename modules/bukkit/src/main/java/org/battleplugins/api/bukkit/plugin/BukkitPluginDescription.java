package org.battleplugins.api.bukkit.plugin;

import mc.euro.version.Version;

import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginDescription;
import org.battleplugins.api.util.MCWrapper;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.Optional;

public class BukkitPluginDescription extends MCWrapper<PluginDescriptionFile> implements PluginDescription {

    public BukkitPluginDescription(PluginDescriptionFile handle) {
        super(handle);
    }

    @Override
    public String getId() {
        return handle.getName().toLowerCase();
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Version<Plugin> getVersion() {
        return new Version<>(handle.getVersion());
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(handle.getDescription());
    }

    @Override
    public Optional<String> getURL() {
        return Optional.ofNullable(handle.getWebsite());
    }

    @Override
    public Optional<String[]> getAuthors() {
        return handle.getAuthors().size() == 0 ? Optional.empty() : Optional.of(handle.getAuthors().toArray(new String[0]));
    }
}
