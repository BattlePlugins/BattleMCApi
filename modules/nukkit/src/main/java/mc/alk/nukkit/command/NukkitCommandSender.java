package mc.alk.nukkit.command;

import cn.nukkit.command.CommandSender;

import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.util.MCWrapper;

public abstract class NukkitCommandSender extends MCWrapper<CommandSender> implements MCCommandSender {

	public NukkitCommandSender(CommandSender sender){
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
