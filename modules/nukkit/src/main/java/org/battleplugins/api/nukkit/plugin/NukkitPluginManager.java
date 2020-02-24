package org.battleplugins.api.nukkit.plugin;

import cn.nukkit.plugin.PluginManager;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.plugin.AbstractPluginManager;
import org.battleplugins.api.plugin.platform.PlatformPlugin;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NukkitPluginManager extends AbstractPluginManager {

    private PluginManager pluginManager;

    @Override
    public List<PlatformPlugin> getPlatformPlugins() {
        return pluginManager.getPlugins().values().stream().map(NukkitPlatformPlugin::new).collect(Collectors.toList());
    }
}
