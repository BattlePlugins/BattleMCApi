package org.battleplugins.api.plugin.platform;

import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.logger.Logger;
import org.battleplugins.api.plugin.PluginDescription;

import java.nio.file.Path;

/**
 * Represents a platform plugin.
 */
public interface PlatformPlugin {

    /**
     * The description of the platform plugin
     *
     * @return the description of the platform plugin
     */
    PluginDescription getPluginDescription();

    /**
     * Registers a command with the given {@link Command} and
     * {@link CommandExecutor}
     *
     * @param command the command to register
     * @param executor the executor of the command
     */
    void registerPluginCommand(Command command, CommandExecutor executor);

    /**
     * The data folder of the plugin
     *
     * @return the data folder of the plugin
     */
    Path getPluginDataFolder();

    /**
     * The {@link Logger}
     *
     * @return the {@link Logger}
     */
    Logger getPluginLogger();

    /**
     * If the plugin is enabled
     *
     * @return if the plugin is enabled
     */
    boolean isPluginEnabled();
}
