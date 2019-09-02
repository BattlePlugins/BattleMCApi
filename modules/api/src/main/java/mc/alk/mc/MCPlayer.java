package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.inventory.MCHumanEntity;

public interface MCPlayer extends MCCommandSender, MCOfflinePlayer, MCHumanEntity {

	String getName();
	String getDisplayName();

	void updateInventory();

	boolean isOnline();

	default void sendMessage(Message message) {
		message.sendMessage(this);
	}
}
