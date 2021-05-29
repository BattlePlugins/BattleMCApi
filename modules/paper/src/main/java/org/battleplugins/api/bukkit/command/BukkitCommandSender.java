package org.battleplugins.api.bukkit.command;

import org.battleplugins.api.util.MCWrapper;
import org.bukkit.command.CommandSender;

public abstract class BukkitCommandSender<T extends CommandSender> extends MCWrapper<T> implements org.battleplugins.api.command.CommandSender {

	public BukkitCommandSender(T sender) {
		super(sender);
	}

	@Override
	public boolean hasPermission(String node) {
		return handle.hasPermission(node);
	}

	@Override
	public void sendMessage(String message) {
		handle.sendMessage(message);
	}

	@Override
	public String getName() {
		return "Console";
	}

	@Override
	public T getHandle() {
		return handle;
	}
}
