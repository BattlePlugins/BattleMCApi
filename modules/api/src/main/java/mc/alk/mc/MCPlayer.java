package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.inventory.MCInventory;

public abstract class MCPlayer implements MCCommandSender, MCOfflinePlayer {

	public abstract MCWorld getWorld();

	public abstract String getName();
	public abstract String getDisplayName();

	public abstract MCInventory getInventory();
	public abstract void updateInventory();

	public abstract boolean isOnline();

	public void sendMessage(Message message) {
		message.sendMessage(this);
	}
}
