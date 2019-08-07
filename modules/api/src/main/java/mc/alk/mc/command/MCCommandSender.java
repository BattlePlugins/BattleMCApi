package mc.alk.mc.command;

public interface MCCommandSender {

	boolean hasPermission(String node);

	void sendMessage(String message);

	String getName();

	boolean isOp();
}
