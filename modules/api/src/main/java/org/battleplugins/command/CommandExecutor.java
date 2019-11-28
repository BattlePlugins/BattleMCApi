package org.battleplugins.command;

public interface CommandExecutor {

    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
