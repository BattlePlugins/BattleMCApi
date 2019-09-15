package mc.alk.bukkit.command;

import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.command.CommandSender;

public abstract class BukkitCommandSender extends MCWrapper<CommandSender> implements MCCommandSender {

	public BukkitCommandSender(CommandSender sender){
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
}
