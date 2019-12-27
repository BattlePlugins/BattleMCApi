package org.battleplugins.api.plugin.platform;

import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.logger.Logger;

import java.io.File;

/**
 * Represents a platform plugin.
 */
public interface PlatformPlugin {

    /**
     * Registers a command with the given {@link Command} and
     * {@link CommandExecutor}
     *
     * @param command the command to register
     * @param executor the executor of the command
     */
    void registerMCCommand(Command command, CommandExecutor executor);

    /**
     * The data folder of the plugin
     *
     * @return the data folder of the plugin
     */
    File getDataFolder();

    /**
     * The {@link Logger}
     *
     * @return the {@link Logger}
     */
    Logger getMCLogger();

    /**
     * If the plugin is enabled
     *
     * @return if the plugin is enabled
     */
    boolean isEnabled();
}
