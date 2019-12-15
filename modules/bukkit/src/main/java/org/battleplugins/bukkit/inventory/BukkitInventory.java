package org.battleplugins.bukkit.inventory;

import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.bukkit.util.BukkitInventoryUtil;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.util.MCWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public class BukkitInventory<T extends Inventory> extends MCWrapper<T> implements org.battleplugins.inventory.Inventory {

	public BukkitInventory(T inventory) {
		super(inventory);
	}

	@Override
	public void addItem(ItemStack itemStack) {
		if (itemStack == null || itemStack.getType().getKey().getKey().equalsIgnoreCase(Material.AIR.name())) {
			return;
		}

		handle.addItem(((BukkitItemStack) itemStack).getHandle());
	}

	@Override
	public void removeItem(ItemStack itemStack) {
		handle.removeItem(((BukkitItemStack) itemStack).getHandle());
	}

	@Override
	public void setItem(int slot, ItemStack item) {
		handle.setItem(slot, ((BukkitItemStack) item).getHandle());
	}

	@Override
	public Optional<BukkitItemStack> getItem(int slot) {
		return Optional.ofNullable(handle.getItem(slot)).map(BukkitItemStack::new);
	}

	@Override
	public int getItemAmount(ItemStack itemStack) {
		return BukkitInventoryUtil.getItemAmountFromInventory(handle,
				((BukkitItemStack) itemStack).getHandle());
	}

	@Override
	public int freeSpaceAfter(ItemStack itemStack) {
		return BukkitInventoryUtil.amountFreeSpace(handle,
				((BukkitItemStack) itemStack).getHandle(), itemStack.getQuantity()) ;
	}

	@Override
	public BukkitItemStack[] getContents() {
		org.bukkit.inventory.ItemStack[] is = handle.getContents();
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

	@Override
	public T getHandle() {
		return handle;
	}
}
