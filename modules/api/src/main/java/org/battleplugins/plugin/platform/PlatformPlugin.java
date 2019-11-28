package org.battleplugins.plugin.platform;

import org.battleplugins.command.Command;
import org.battleplugins.command.CommandExecutor;
import org.battleplugins.logger.Logger;

import java.io.File;

public interface PlatformPlugin {

    void registerMCCommand(Command command, CommandExecutor executor);

    File getDataFolder();

    Logger getMCLogger();

    boolean isEnabled();
}
