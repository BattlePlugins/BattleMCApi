package mc.alk.sponge.plugin;

import com.google.inject.Inject;
import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.sponge.SpongeServer;
import mc.alk.sponge.command.SpongeCommandExecutor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;
import org.spongepowered.api.plugin.PluginManager;

import java.io.File;
import java.util.List;

public abstract class SpongePlugin implements MCPlugin {

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;

    @Inject
    private PluginManager pluginManager;

    private MCServer server;
    private boolean enabled = false;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        onEnable();
        enabled = true;
    }

    @Listener
    public void onServerStop(GameStoppedEvent event) {
        enabled = false;
        onDisable();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public MCServer getMCServer() {
        if (server == null) {
            server = new SpongeServer();
            MCServer.setInstance(server);
        }

        return server;
    }

    @Override
    public File getDataFolder() {
        return configDir;
    }

    @Override
    public String getName() {
        return pluginManager.fromInstance(this).get().getName();
    }

    @Override
    public List<String> getAuthors() {
        return pluginManager.fromInstance(this).get().getAuthors();
    }

    @Override
    public String getVersion() {
        return pluginManager.fromInstance(this).get().getVersion().get();
    }

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        List<String> aliases = command.getAliases();
        aliases.add(command.getLabel());
        Sponge.getCommandManager().register(this, new SpongeCommandExecutor(executor, command), aliases);
    }
}
