package org.battleplugins.plugin;

import org.battleplugins.APIType;
import org.battleplugins.Platform;
import org.battleplugins.command.Command;
import org.battleplugins.command.CommandExecutor;
import org.battleplugins.logger.Logger;
import org.battleplugins.plugin.platform.PlatformCodeHandler;
import org.battleplugins.plugin.platform.PlatformPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Plugin {

	protected PlatformPlugin platformPlugin;
	protected Map<APIType, PlatformCodeHandler> platformCode;

	public abstract void onEnable();
	public abstract void onDisable();

	public Plugin() {
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

	public Logger getLogger() {
		return platformPlugin.getMCLogger();
	}

	public void registerCommand(Command command, CommandExecutor executor) {
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
		return platformCode.get(Platform.getAPI());
	}

	public Platform getPlatform() {
		return Platform.getPlatform();
	}
}
