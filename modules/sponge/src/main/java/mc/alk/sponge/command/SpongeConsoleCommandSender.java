package mc.alk.sponge.command;

import org.spongepowered.api.command.CommandSource;

public class SpongeConsoleCommandSender extends SpongeCommandSender {

    public SpongeConsoleCommandSender(CommandSource sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
