package org.battleplugins.entity.living.player;

import org.battleplugins.entity.living.player.gamemode.GameMode;
import org.battleplugins.message.Message;
import org.battleplugins.command.CommandSender;
import org.battleplugins.entity.living.HumanEntity;
import org.battleplugins.inventory.Inventory;

/**
 * Represents a player.
 */
public interface Player extends CommandSender, OfflinePlayer, HumanEntity {

	/**
	 * The display name of the player
	 *
	 * @return the display name of the player
	 */
	String getDisplayName();

	/**
	 * Opens the given {@link Inventory}
	 *
	 * @param inventory the inventory to open
	 */
	void openInventory(Inventory inventory);

	/**
	 * Updates the player's {@link Inventory}
	 */
	void updateInventory();

	/**
	 * Sends a {@link Message} to the player
	 *
	 * @param message the message to send
	 */
	default void sendMessage(Message message) {
		message.sendMessage(this);
	}

	/**
	 * The player's {@link GameMode}
	 *
	 * @return the player's gamemode
	 */
	GameMode getGameMode();

	/**
	 * Sets the player's {@link GameMode}
	 *
	 * @param gameMode the gamemode to set for the player
	 */
	void setGameMode(GameMode gameMode);
}
