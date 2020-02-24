package org.battleplugins.api.sponge.plugin;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.plugin.AbstractPluginManager;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.spongepowered.api.plugin.PluginManager;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SpongePluginManager extends AbstractPluginManager {

    private PluginManager pluginManager;

    @Override
    public List<PlatformPlugin> getPlatformPlugins() {
        return pluginManager.getPlugins().stream().map(SpongePlatformPlugin::new).collect(Collectors.toList());
    }
}
