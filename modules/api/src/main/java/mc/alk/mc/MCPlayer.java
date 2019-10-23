package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.entity.MCHumanEntity;
import mc.alk.mc.entity.player.MCGameMode;
import mc.alk.mc.inventory.MCInventory;

public interface MCPlayer extends MCCommandSender, MCOfflinePlayer, MCHumanEntity {

	String getName();
	String getDisplayName();

	void openInventory(MCInventory inventory);
	void updateInventory();

	boolean isOnline();

	default void sendMessage(Message message) {
		message.sendMessage(this);
	}

	MCGameMode getGameMode();
	void setGameMode(MCGameMode gameMode);
}
