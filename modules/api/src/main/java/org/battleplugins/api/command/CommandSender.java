package org.battleplugins.api.command;

import net.kyori.adventure.text.Component;

/**
 * An object capable of executing commands.
 */
public interface CommandSender {

	/**
	 * If the sender has the given permission node
	 *
	 * @param node the permission node
	 * @return if the sender has the given permission node
	 */
	boolean hasPermission(String node);

	/**
	 * Sends a message to the sender
	 *
	 * @param message the message to send
	 */
	void sendMessage(String message);

	/**
	 * Sends a message in the form of a
	 * {@link Component} to the sender
	 *
	 * @param message the message to send
	 */
	void sendMessage(Component message);

	/**
	 * The name of the sender
	 *
	 * @return the name of the sender
	 */
	String getName();

	/**
	 * If the sender is an operator
	 *
	 * @return if the sender is an operator
	 */
	boolean isOp();
}
