package org.battleplugins.api.bukkit.plugin;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.plugin.AbstractPluginManager;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BukkitPluginManager extends AbstractPluginManager {

    private PluginManager pluginManager;

    @Override
    public List<PlatformPlugin> getPlatformPlugins() {
        return Arrays.stream(pluginManager.getPlugins()).map(BukkitPlatformPlugin::new).collect(Collectors.toList());
    }
}
