package mc.alk.mc.plugin;

import mc.alk.mc.MCServer;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;

import java.io.File;
import java.util.List;

public interface MCPlugin {

	MCServer getMCServer();

	void onEnable();
	void onDisable();

	boolean isEnabled();

	File getDataFolder();

	String getName();
	List<String> getAuthors();
	String getVersion();

	void registerMCCommand(MCCommand command, MCCommandExecutor executor);
}
