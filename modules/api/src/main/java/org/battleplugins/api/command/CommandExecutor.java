package org.battleplugins.api.command;

/**
 * A class containing a single method for executing commands.
 */
public interface CommandExecutor {

    /**
     * Executes the given command
     *
     * @param sender the {@link CommandSender} of the command
     * @param command the {@link Command} executed
     * @param label the command label
     * @param args the arguments of the command
     * @return if the command was successfully executed
     */
    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
