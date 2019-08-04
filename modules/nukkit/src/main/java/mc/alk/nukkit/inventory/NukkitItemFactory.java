package mc.alk.nukkit.inventory;

import cn.nukkit.item.Item;
import mc.alk.nukkit.util.NukkitInventoryUtil;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.ItemFactory;

public class NukkitItemFactory extends ItemFactory {

	@Override
	public MCItemStack createMCItem(String text) {
		return new NukkitItemStack(Item.fromString(text));
	}

	@Override
	public MCItemStack createMCItem(String type, short value, int quantity) {
		return new NukkitItemStack(new Item(Item.fromString(type).getId(), quantity, value));
	}
}
