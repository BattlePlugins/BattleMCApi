package org.battleplugins.sponge.command;

import org.battleplugins.util.MCWrapper;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

public abstract class SpongeCommandSender<T extends CommandSource> extends MCWrapper<T> implements org.battleplugins.command.CommandSender {

    public SpongeCommandSender(T sender) {
        super(sender);
    }

    @Override
    public boolean hasPermission(String node) {
        return handle.hasPermission(node);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(Text.of(message));
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
