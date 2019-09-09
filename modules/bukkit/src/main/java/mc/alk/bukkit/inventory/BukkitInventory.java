package mc.alk.bukkit.inventory;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BukkitInventory implements MCInventory {

	private Inventory inventory;

	public BukkitInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void addItem(MCItemStack... itemStacks) {
		for (MCItemStack item: itemStacks)
			addItem(item);
	}

	public void addItem(MCItemStack itemStack) {
		if (itemStack != null || itemStack.getType().equals(Material.AIR.name())) {
			return;
		}

		BukkitInventoryUtil.addItemToInventory(inventory, ((BukkitItemStack)itemStack).getBukkitItemStack(),itemStack.getQuantity());
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		BukkitInventoryUtil.removeItem(inventory,
				((BukkitItemStack)itemStack).getBukkitItemStack()) ;
	}

	@Override
	public void setItem(int slot, MCItemStack item) {
		inventory.setItem(slot, ((BukkitItemStack) item).getBukkitItemStack());
	}

	@Override
	public MCItemStack getItem(int slot) {
		return new BukkitItemStack(inventory.getItem(slot));
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		return BukkitInventoryUtil.getItemAmountFromInventory(inventory,
				((BukkitItemStack)itemStack).getBukkitItemStack()) ;
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		int space = BukkitInventoryUtil.amountFreeSpace(inventory,
				((BukkitItemStack)itemStack).getBukkitItemStack(), itemStack.getQuantity()) ;
		return space >= 0;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return BukkitInventoryUtil.amountFreeSpace(inventory,
				((BukkitItemStack)itemStack).getBukkitItemStack(), itemStack.getQuantity()) ;
	}

	@Override
	public MCItemStack[] getContents() {
		ItemStack[] is = inventory.getContents();
		MCItemStack[] items = new MCItemStack[is.length];
		for (int i=0;i< is.length;i++){
			items[i] = new BukkitItemStack(is[i]);
		}
		return items;
	}

	public Inventory getBukkitInventory() {
		return inventory;
	}
}
