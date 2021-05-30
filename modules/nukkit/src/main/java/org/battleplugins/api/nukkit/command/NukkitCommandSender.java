package org.battleplugins.api.nukkit.command;

import cn.nukkit.command.CommandSender;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.battleplugins.api.util.MCWrapper;

public abstract class NukkitCommandSender<T extends CommandSender> extends MCWrapper<T> implements org.battleplugins.api.command.CommandSender {

	public NukkitCommandSender(T sender){
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
	public void sendMessage(Component message) {
		handle.sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
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
