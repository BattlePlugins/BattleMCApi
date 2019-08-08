package mc.alk.mc;

import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.inventory.MCInventory;

public interface MCPlayer extends MCCommandSender, MCOfflinePlayer {

	MCWorld getWorld();

	String getName();
	String getDisplayName();

	MCInventory getInventory();
	void updateInventory();

	boolean isOnline();
}
