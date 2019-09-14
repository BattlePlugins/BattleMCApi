package mc.alk.sponge.plugin;

import com.google.inject.Inject;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.PlatformPlugin;
import mc.alk.mc.plugin.PluginConstants;
import mc.alk.sponge.SpongePlatform;
import mc.alk.sponge.command.SpongeCommandExecutor;
import mc.alk.sponge.logger.SpongeLogger;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginManager;

import java.io.File;
import java.util.List;

@Plugin(id = PluginConstants.ID, name = PluginConstants.NAME, version = PluginConstants.VERSION,
        description = PluginConstants.DESCRIPTION, url = PluginConstants.URL, authors = PluginConstants.AUTHORS)
public abstract class SpongePlugin implements PlatformPlugin {

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;

    @Inject
    private PluginManager pluginManager;

    private MCPlatform platform;
    private boolean enabled = false;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        MCPlatform.getPluginManager().initializePlugin(this);
        MCPlatform.getPluginManager().enablePlugin();
        onEnable();
        enabled = true;
    }

    @Listener
    public void onServerStop(GameStoppedEvent event) {
        enabled = false;
        MCPlatform.getPluginManager().disablePlugin();
        onDisable();
    }


    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public MCPlatform getPlatform() {
        if (platform == null) {
            platform = new SpongePlatform();
            MCPlatform.setInstance(platform);
        }

        return platform;
    }

    public File getDataFolder() {
        return configDir;
    }

    public String getName() {
        return pluginManager.fromInstance(this).get().getName();
    }

    public List<String> getAuthors() {
        return pluginManager.fromInstance(this).get().getAuthors();
    }

    public String getVersion() {
        return pluginManager.fromInstance(this).get().getVersion().get();
    }

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        List<String> aliases = command.getAliases();
        aliases.add(command.getLabel());
        Sponge.getCommandManager().register(this, new SpongeCommandExecutor(executor, command), aliases);
    }

    @Override
    public MCLogger getMCLogger() {
        return new SpongeLogger(logger);
    }
}
