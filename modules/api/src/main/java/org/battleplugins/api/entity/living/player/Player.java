package org.battleplugins.api.entity.living.player;

import org.battleplugins.api.command.CommandSender;
import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.entity.living.player.gamemode.GameMode;
import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.inventory.entity.PlayerInventory;
import org.battleplugins.api.message.Message;

/**
 * Represents a player.
 */
public interface Player extends CommandSender, OfflinePlayer, Human {

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

	/**
	 * The current {@link Hand} the
	 * player has selected
	 *
	 * @return the current hand the player has selected
	 */
	Hand getHand();

	/**
	 * The inventory of the player
	 *
	 * @return the inventory of the player
	 */
	@Override
	PlayerInventory getInventory();
}
