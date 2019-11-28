package org.battleplugins.nukkit.command;

import cn.nukkit.command.ConsoleCommandSender;

public class NukkitConsoleCommandSender extends NukkitCommandSender<ConsoleCommandSender> {

    public NukkitConsoleCommandSender(ConsoleCommandSender sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
