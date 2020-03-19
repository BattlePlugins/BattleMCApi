package org.battleplugins.api.nukkit.plugin;

import cn.nukkit.plugin.PluginBase;

import org.battleplugins.api.Platform;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.common.event.AbstractEventBus;
import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.nukkit.NukkitPlatform;
import org.battleplugins.api.nukkit.event.NukkitEventListener;
import org.battleplugins.api.nukkit.inventory.virtual.VirtualInventoryListener;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginDescription;
import org.battleplugins.api.plugin.platform.PlatformPlugin;

import java.nio.file.Path;

public class NukkitPlugin extends PluginBase implements PlatformPlugin {

    private Plugin plugin;
    private PlatformPlugin platformPlugin;

    @Override
    public void onEnable() {
        this.platformPlugin = new NukkitPlatformPlugin(this);
        this.getServer().getPluginManager().registerEvents(new VirtualInventoryListener(this), this);

        AbstractEventBus eventBus = new AbstractEventBus();
        NukkitPluginManager pluginManager = new NukkitPluginManager(this.getServer().getPluginManager());
        NukkitPlatform platform = new NukkitPlatform(this.getServer());
        Platform.setInstance(platform, eventBus, pluginManager);
        platform.getPlatformRegistry().setup();

        Platform.getPluginManager().enablePlugin(this.plugin = Platform.getPluginManager().initializePlugin(this));

        EventFactory eventFactory = new EventFactory(eventBus);
        this.getServer().getPluginManager().registerEvents(new NukkitEventListener(eventFactory), this);
    }

    @Override
    public void onDisable() {
        Platform.getPluginManager().disablePlugin(this.plugin);
    }

    @Override
    public PluginDescription getPluginDescription() {
        return platformPlugin.getPluginDescription();
    }

    @Override
    public void registerPluginCommand(Command command, CommandExecutor executor) {
        platformPlugin.registerPluginCommand(command, executor);
    }

    @Override
    public Path getPluginDataFolder() {
        return platformPlugin.getPluginDataFolder();
    }

    @Override
    public Logger getPluginLogger() {
        return platformPlugin.getPluginLogger();
    }

    @Override
    public boolean isPluginEnabled() {
        return platformPlugin.isPluginEnabled();
    }
}
