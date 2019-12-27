package org.battleplugins.api.sponge.command;

import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.command.CommandSender;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.sponge.entity.living.player.SpongePlayer;
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

public class SpongeCommandExecutor extends MCWrapper<CommandExecutor> implements CommandCallable {

    private Command command;

    public SpongeCommandExecutor(CommandExecutor executor, Command command) {
        super(executor);

        this.command = command;
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) {
        CommandSender mcSender;
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
