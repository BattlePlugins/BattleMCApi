package mc.alk.mc.plugin.platform;

import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;

import java.io.File;

public interface PlatformPlugin {

    void registerMCCommand(MCCommand command, MCCommandExecutor executor);

    File getDataFolder();

    MCLogger getMCLogger();

    boolean isEnabled();
}
