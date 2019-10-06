package mc.alk.mc.plugin;

import mc.alk.mc.APIType;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.command.MCCommand;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.logger.MCLogger;
import mc.alk.mc.plugin.platform.PlatformCodeHandler;
import mc.alk.mc.plugin.platform.PlatformPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class MCPlugin {

	protected PlatformPlugin platformPlugin;
	protected Map<APIType, PlatformCodeHandler> platformCode;

	public abstract void onEnable();
	public abstract void onDisable();

	public MCPlugin() {
		this.platformCode = new HashMap<>();
		for (APIType type : APIType.values()) {
			platformCode.put(type, new PlatformCodeHandler());
		}
	}

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

	public PluginDescription getDescription() {
		return new PluginDescription(getClass().getAnnotation(PluginProperties.class));
	}

	public PlatformCodeHandler getPlatformCode() {
		return platformCode.get(MCPlatform.getAPI());
	}

	public MCPlatform getPlatform() {
		return MCPlatform.getPlatform();
	}
}
