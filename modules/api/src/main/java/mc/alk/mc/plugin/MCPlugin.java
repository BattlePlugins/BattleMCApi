package mc.alk.mc.plugin;

import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;

import java.io.File;

public interface MCPlugin {

	MCServer getMCServer();

	void onEnable();
	void onDisable();

	boolean isEnabled();

	File getDataFolder();
	
	void registerMCCommand(MCCommand command, MCCommandExecutor executor);
}
