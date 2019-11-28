package org.battleplugins.bukkit.command;

import org.bukkit.command.Command;

public class BukkitCommand extends org.battleplugins.command.Command {

    public BukkitCommand(Command command) {
        super(command.getLabel(), command.getDescription(), command.getPermission(), command.getAliases());
    }
}
