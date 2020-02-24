package org.battleplugins.api.nukkit.plugin;

import cn.nukkit.plugin.PluginDescription;

import mc.euro.version.Version;

import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.util.MCWrapper;

import java.util.Optional;

public class NukkitPluginDescription extends MCWrapper<PluginDescription> implements org.battleplugins.api.plugin.PluginDescription {

    public NukkitPluginDescription(PluginDescription handle) {
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
