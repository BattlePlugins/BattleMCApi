package mc.alk.mc.inventory;

import mc.alk.bukkit.inventory.BukkitItemFactory;
import mc.alk.mc.MCServer;

public abstract class ItemFactory {

	public abstract MCItemStack createMCItem(String type, short value, int quantity);
	public abstract MCItemStack createMCItem(String text);

	public static MCItemStack createItem(String type, short value, int quantity) {
		switch (MCServer.getType()) {
			case TEST:
			case BUKKIT:
				return new BukkitItemFactory().createMCItem(type, value, quantity);
			default:
				return null;
		}
	}

	public static MCItemStack createItem(String text) {
		switch (MCServer.getType()) {
			case TEST:
			case BUKKIT:
				return new BukkitItemFactory().createMCItem(text);
			default:
				return null;
		}
	}
}
