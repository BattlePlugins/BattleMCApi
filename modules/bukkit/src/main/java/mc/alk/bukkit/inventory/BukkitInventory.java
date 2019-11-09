package mc.alk.bukkit.inventory;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BukkitInventory extends MCWrapper<Inventory> implements MCInventory {

	public BukkitInventory(Inventory inventory) {
		super(inventory);
	}

	@Override
	public void addItem(MCItemStack... itemStacks) {
		for (MCItemStack item: itemStacks)
			addItem(item);
	}

	public void addItem(MCItemStack itemStack) {
		if (itemStack == null || itemStack.getType().equals(Material.AIR.name())) {
			return;
		}

		BukkitInventoryUtil.addItemToInventory(handle, ((BukkitItemStack) itemStack).getHandle(),itemStack.getQuantity());
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		BukkitInventoryUtil.removeItem(handle,
				((BukkitItemStack)itemStack).getHandle()) ;
	}

	@Override
	public void setItem(int slot, MCItemStack item) {
		handle.setItem(slot, ((BukkitItemStack) item).getHandle());
	}

	@Override
	public BukkitItemStack getItem(int slot) {
		return new BukkitItemStack(handle.getItem(slot));
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		return BukkitInventoryUtil.getItemAmountFromInventory(handle,
				((BukkitItemStack) itemStack).getHandle()) ;
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		int space = BukkitInventoryUtil.amountFreeSpace(handle,
				((BukkitItemStack) itemStack).getHandle(), itemStack.getQuantity()) ;
		return space >= 0;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return BukkitInventoryUtil.amountFreeSpace(handle,
				((BukkitItemStack) itemStack).getHandle(), itemStack.getQuantity()) ;
	}

	@Override
	public BukkitItemStack[] getContents() {
		ItemStack[] is = handle.getContents();
		BukkitItemStack[] items = new BukkitItemStack[is.length];
		for (int i = 0; i < is.length; i++){
			items[i] = new BukkitItemStack(is[i]);
		}
		return items;
	}

	@Override
	public void clear() {
		handle.clear();
	}
}
