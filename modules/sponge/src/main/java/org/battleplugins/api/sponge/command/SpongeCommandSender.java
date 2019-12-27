package org.battleplugins.api.sponge.command;

import org.battleplugins.api.command.CommandSender;
import org.battleplugins.api.util.MCWrapper;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

public abstract class SpongeCommandSender<T extends CommandSource> extends MCWrapper<T> implements CommandSender {

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
