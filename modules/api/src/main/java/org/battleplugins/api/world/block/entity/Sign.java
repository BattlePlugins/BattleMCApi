package org.battleplugins.api.world.block.entity;

import org.battleplugins.api.entity.living.player.Player;

/**
 * Represents a sign.
 */
public interface Sign extends BlockEntity {

	/**
	 * The line at the given index
	 *
	 * @param index the line number (0-3)
	 * @return the line at the given index
	 */
	default String getLine(int index) {
		return getLines()[index];
	}

	/**
	 * A string array of all the lines
	 *
	 * @return a string array of all the lines
	 */
	String[] getLines();

	/**
	 * Sets the text at the given line
	 *
	 * @param index the line number (0-3)
	 * @param text the text to set
	 */
	void setLine(int index, String text);

	/**
	 * Sends a temporary sign update to the player. This
	 * is not persistent and only lasts until the player
	 * logs out, chunks unload, or the sign is updated
	 * by something else
	 *
	 * @param player the player to send the sign change for
	 * @param lines the lines to update
	 */
	void sendSignUpdate(Player player, String[] lines);
}
