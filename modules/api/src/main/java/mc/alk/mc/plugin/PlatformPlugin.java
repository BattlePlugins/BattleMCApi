package mc.alk.mc.plugin;

import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;

import java.io.File;
import java.util.List;

public interface PlatformPlugin {

    void registerMCCommand(MCCommand command, MCCommandExecutor executor);

    File getDataFolder();

    String getName();
    List<String> getAuthors();
    String getVersion();

    MCPlatform getPlatform();
    MCLogger getMCLogger();

    void onEnable();
    void onDisable();

    boolean isEnabled();
}
