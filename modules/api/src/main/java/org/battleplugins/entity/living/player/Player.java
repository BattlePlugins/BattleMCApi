package org.battleplugins.entity.living.player;

import org.battleplugins.message.Message;
import org.battleplugins.command.CommandSender;
import org.battleplugins.entity.living.HumanEntity;
import org.battleplugins.inventory.Inventory;

public interface Player extends CommandSender, OfflinePlayer, HumanEntity {

	String getName();
	String getDisplayName();

	void openInventory(Inventory inventory);
	void updateInventory();

	boolean isOnline();

	default void sendMessage(Message message) {
		message.sendMessage(this);
	}

	GameMode getGameMode();
	void setGameMode(GameMode gameMode);
}
