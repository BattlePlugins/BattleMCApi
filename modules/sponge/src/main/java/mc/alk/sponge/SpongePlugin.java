package mc.alk.sponge;

import mc.alk.mc.plugin.MCPlugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;

public abstract class SpongePlugin implements MCPlugin {

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
}
