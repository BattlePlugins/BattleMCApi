package mc.alk.nukkit.command;

import cn.nukkit.command.CommandSender;

import mc.alk.mc.command.MCCommandSender;

public class NukkitCommandSender implements MCCommandSender {

	private CommandSender sender;

	public NukkitCommandSender(CommandSender sender){
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

	public CommandSender getNukkitCommandSender() {
		return sender;
	}
}
