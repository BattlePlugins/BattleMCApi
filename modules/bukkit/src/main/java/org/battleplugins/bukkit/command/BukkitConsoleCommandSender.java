package org.battleplugins.bukkit.command;

import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsoleCommandSender extends BukkitCommandSender<ConsoleCommandSender> {

    public BukkitConsoleCommandSender(ConsoleCommandSender sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
