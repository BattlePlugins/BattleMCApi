package org.battleplugins.api.plugin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.battleplugins.api.Platform;
import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;
import org.battleplugins.api.Server;
import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.event.EventBus;
import org.battleplugins.api.plugin.platform.PlatformCodeHandler;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.battleplugins.api.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a plugin that can be enabled/disabled.
 */
public abstract class Plugin {

	/**
	 * The {@link PlatformPlugin}
	 *
	 * @return the {@link PlatformPlugin}
	 */
	@Getter
	@Setter
	protected PlatformPlugin platformPlugin;

	protected Map<PlatformType, PlatformCodeHandler> platformCode;

	public Plugin() {
		this.platformCode = new HashMap<>();
		for (PlatformType type : PlatformTypes.values()) {
			platformCode.put(type, new PlatformCodeHandler());
		}
	}

	/**
	 * Called when the plugin is enabled
	 */
	public abstract void onEnable();

	/**
	 * Called when the plugin is disabled
	 */
	public abstract void onDisable();

	/**
	 * If the plugin is enabled
	 *
	 * @return if the plugin is enabled
	 */
	public boolean isEnabled() {
		return platformPlugin.isPluginEnabled();
	}

	/**
	 * The data folder of the plugin
	 *
	 * @return the data folder of the plugin
	 */
	public File getDataFolder() {
		return platformPlugin.getPluginDataFolder();
	}

	/**
	 * The {@link Logger}
	 *
	 * @return the {@link Logger}
	 */
	public Logger getLogger() {
		return platformPlugin.getPluginLogger();
	}

	/**
	 * Registers a command with the given {@link Command} and
	 * {@link CommandExecutor}
	 *
	 * @param command the command to register
	 * @param executor the executor of the command
	 */
	public void registerCommand(Command command, CommandExecutor executor) {
		platformPlugin.registerPluginCommand(command, executor);
	}

	/**
	 * The {@link PluginDescription} containing information
	 * about the plugin
	 *
	 * @return the {@link PluginDescription}
	 */
	public PluginPropertiesDescription getDescription() {
		return new PluginPropertiesDescription(getClass().getAnnotation(PluginProperties.class));
	}

	/**
	 * The {@link PlatformCodeHandler} for the given platform the
	 * plugin is currently running on
	 *
	 * @return the {@link PlatformCodeHandler}
	 */
	public PlatformCodeHandler getPlatformCode() {
		return platformCode.get(Platform.getPlatformType());
	}

	/**
	 * The {@link Platform}
	 *
	 * @return the {@link Platform}
	 */
	public Platform getPlatform() {
		return Platform.getPlatform();
	}

	/**
	 * The {@link Server}
	 *
	 * @return the {@link Server}
	 */
	public Server getServer() {
		return Platform.getServer();
	}

	/**
	 * The {@link EventBus}
	 *
	 * @return the {@link EventBus}
	 */
	public EventBus getEventBus() {
		return Platform.getEventBus();
	}
}
