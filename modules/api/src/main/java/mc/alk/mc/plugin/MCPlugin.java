package mc.alk.mc.plugin;

import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommandExecutor;

public interface MCPlugin {

	MCServer getMCServer();

	void onEnable();
	void onDisable();

	boolean isEnabled();

	void registerMCCommand(String command, MCCommandExecutor executor);
}
