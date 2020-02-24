package org.battleplugins.api.sponge.plugin;

import com.google.inject.Inject;

import org.battleplugins.api.Platform;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.common.event.AbstractEventBus;
import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginDescription;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.battleplugins.api.sponge.SpongePlatform;
import org.battleplugins.api.sponge.event.SpongeEventListener;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

public class AbstractSpongePlugin implements PlatformPlugin {

    @Inject
    private Game game;

    @Inject
    private org.slf4j.Logger logger;

    @Inject
    private PluginContainer container;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;

    private Plugin plugin;
    private PlatformPlugin platformPlugin;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.platformPlugin = new SpongePlatformPlugin(container);
        AbstractEventBus eventBus = new AbstractEventBus();
        SpongePluginManager pluginManager = new SpongePluginManager(game.getPluginManager());
        SpongePlatform platform = new SpongePlatform(game.getServer());
        Platform.setInstance(platform, eventBus, pluginManager);
        platform.getPlatformRegistry().setup();

        Platform.getPluginManager().enablePlugin(this.plugin = Platform.getPluginManager().initializePlugin(this));

        EventFactory eventFactory = new EventFactory(eventBus);
        game.getEventManager().registerListeners(this, new SpongeEventListener(eventFactory));
    }

    @Listener
    public void onServerStop(GameStoppedEvent event) {
        Platform.getPluginManager().disablePlugin(this.plugin);
    }

    @Override
    public boolean isPluginEnabled() {
        return platformPlugin.isPluginEnabled();
    }

    @Override
    public File getPluginDataFolder() {
        return configDir;
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
    public Logger getPluginLogger() {
        return platformPlugin.getPluginLogger();
    }
}
