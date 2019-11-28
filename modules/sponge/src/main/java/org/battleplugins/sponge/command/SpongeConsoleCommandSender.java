package org.battleplugins.sponge.command;

import org.spongepowered.api.command.CommandSource;

public class SpongeConsoleCommandSender extends SpongeCommandSender<CommandSource> {

    public SpongeConsoleCommandSender(CommandSource sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
