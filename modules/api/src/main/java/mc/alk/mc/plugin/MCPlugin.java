package mc.alk.mc.plugin;

import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;

import java.io.File;

public abstract class MCPlugin {

	private PlatformPlugin platformPlugin;

	private boolean enabled = false;

	public abstract void onEnable();
	public abstract void onDisable();

	public boolean isEnabled() {
		return platformPlugin.isEnabled();
	}

	public File getDataFolder() {
		return platformPlugin.getDataFolder();
	}

	public MCLogger getLogger() {
		return platformPlugin.getMCLogger();
	}

	public void registerCommand(MCCommand command, MCCommandExecutor executor) {
		platformPlugin.registerMCCommand(command, executor);
	}

	public PlatformPlugin getPlatformPlugin() {
		return platformPlugin;
	}

	protected void setPlatformPlugin(PlatformPlugin platformPlugin) {
		this.platformPlugin = platformPlugin;
	}
}
