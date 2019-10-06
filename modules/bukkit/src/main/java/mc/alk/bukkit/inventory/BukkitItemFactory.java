package mc.alk.bukkit.inventory;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCItemFactory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BukkitItemFactory extends MCItemFactory {

	@Override
	public MCItemStack createMCItem(String type, short value, int quantity) {
		return new BukkitItemStack(new ItemStack(Material.matchMaterial(type), quantity,value));
	}

	@Override
	public MCItemStack createMCItem(String text) {
		ItemStack item = BukkitInventoryUtil.getItemStack(text);
		return item == null ? null : new BukkitItemStack(item);
	}
}
