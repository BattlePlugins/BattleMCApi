package org.battleplugins.world.block.entity;

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
}
