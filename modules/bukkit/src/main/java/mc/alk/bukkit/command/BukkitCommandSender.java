package mc.alk.bukkit.command;

import mc.alk.mc.command.MCCommandSender;
import org.bukkit.command.CommandSender;

public abstract class BukkitCommandSender implements MCCommandSender {

	private CommandSender sender;

	public BukkitCommandSender(CommandSender sender){
		this.sender = sender;
	}

	@Override
	public boolean hasPermission(String node) {
		return sender.hasPermission(node);
	}

	@Override
	public void sendMessage(String message) {
		sender.sendMessage(message);
	}

	@Override
	public String getName() {
		return "Console";
	}

	public CommandSender getBukkitCommandSender() {
		return sender;
	}
}
