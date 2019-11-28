package org.battleplugins.command;

public interface CommandSender {

	boolean hasPermission(String node);

	void sendMessage(String message);

	String getName();

	boolean isOp();
}
