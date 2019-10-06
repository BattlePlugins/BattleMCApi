package mc.alk.nukkit.inventory;

import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCItemFactory;

public class NukkitItemFactory extends MCItemFactory {

	@Override
	public MCItemStack createMCItem(String text) {
		return new NukkitItemStack(Item.fromString(text));
	}

	@Override
	public MCItemStack createMCItem(String type, short value, int quantity) {
		return new NukkitItemStack(new Item(Item.fromString(type).getId(), quantity, value));
	}
}
