package mc.alk.sponge.command;

import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

/**
 * This class is a bit more complex than the rest due to how
 * the Sponge API is written compared to Bukkit/Nukkit.
 */
public class SpongeCommandExecutor implements CommandExecutor {

    private MCCommandExecutor executor;
    private MCCommand command;

    public SpongeCommandExecutor(MCCommandExecutor executor, MCCommand command) {
        this.executor = executor;
        this.command = command;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        // TODO: Find a way to get args
        // executor.onCommand(new SpongeCommandSender(src), command, command.getLabel(), this.args);
        return CommandResult.success();
    }
}
