package mc.alk.sponge.plugin;

import mc.alk.mc.MCServer;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.sponge.SpongeServer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;

public abstract class SpongePlugin implements MCPlugin {

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
}
