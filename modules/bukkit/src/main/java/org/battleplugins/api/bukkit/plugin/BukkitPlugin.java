package org.battleplugins.api.bukkit.plugin;

import org.battleplugins.api.Platform;
import org.battleplugins.api.bukkit.BukkitPlatform;
import org.battleplugins.api.bukkit.event.BukkitEventListener;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.common.event.AbstractEventBus;
import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginDescription;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public class BukkitPlugin extends JavaPlugin implements PlatformPlugin {

    protected Plugin plugin;
    protected PlatformPlugin platformPlugin;

    @Override
    public void onEnable() {
        this.platformPlugin = new BukkitPlatformPlugin(this);

        AbstractEventBus eventBus = new AbstractEventBus();
        BukkitPluginManager pluginManager = new BukkitPluginManager(this.getServer().getPluginManager());
        BukkitPlatform platform = new BukkitPlatform(this.getServer());
        Platform.setInstance(platform, eventBus, pluginManager);
        platform.getPlatformRegistry().setup();

        Platform.getPluginManager().enablePlugin(this.plugin = Platform.getPluginManager().initializePlugin(this));

        EventFactory eventFactory = new EventFactory(eventBus);
        this.getServer().getPluginManager().registerEvents(new BukkitEventListener(eventFactory), this);
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
