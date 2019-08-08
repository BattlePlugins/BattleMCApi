package mc.alk.sponge.plugin;

import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.sponge.SpongeServer;
import mc.alk.sponge.command.SpongeCommandExecutor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void registerMCCommand(MCCommand command, MCCommandExecutor executor) {
        List<String> aliases = command.getAliases();
        aliases.add(command.getLabel());
        Sponge.getCommandManager().register(this, new SpongeCommandExecutor(executor, command), aliases);
    }
}
