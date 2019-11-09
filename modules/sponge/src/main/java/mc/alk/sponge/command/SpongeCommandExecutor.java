package mc.alk.sponge.command;

import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.util.MCWrapper;
import mc.alk.sponge.SpongePlayer;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpongeCommandExecutor extends MCWrapper<MCCommandExecutor> implements CommandCallable {

    private MCCommand command;

    public SpongeCommandExecutor(MCCommandExecutor executor, MCCommand command) {
        super(executor);

        this.command = command;
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) {
        MCCommandSender mcSender;
        if (source instanceof Player)
            mcSender = new SpongePlayer((Player) source);
        else
            mcSender = new SpongeConsoleCommandSender(source);

        handle.onCommand(mcSender, command, command.getLabel(), arguments.split(" "));
        return CommandResult.success();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition) throws CommandException {
        return new ArrayList<>();
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return source.hasPermission(command.getPermission());
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of(command.getDescription()));
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        return Optional.of(Text.of("/" + command.getLabel()));
    }

    @Override
    public Text getUsage(CommandSource source) {
        return Text.of("/" + command.getLabel());
    }
}
