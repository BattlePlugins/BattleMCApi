package mc.alk.nukkit.inventory;

import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.nukkit.util.NukkitInventoryUtil;

import java.util.ArrayList;
import java.util.List;

public class NukkitInventory implements MCInventory {

	private Inventory inventory;

	public NukkitInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void addItem(MCItemStack... itemStacks) {
		for (MCItemStack item: itemStacks)
			addItem(item);
	}

	public void addItem(MCItemStack itemStack) {
		if (itemStack != null || itemStack.getType().equals("0")) {
			return;
		}

		NukkitInventoryUtil.addItemToInventory(inventory, ((NukkitItemStack)itemStack).getNukkitItem(),itemStack.getQuantity());
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		inventory.removeItem(((NukkitItemStack) itemStack).getNukkitItem());
	}

	@Override
	public void setItem(int slot, MCItemStack item) {
		inventory.setItem(slot, ((NukkitItemStack) item).getNukkitItem());
	}

	@Override
	public MCItemStack getItem(int slot) {
		return new NukkitItemStack(inventory.getItem(slot));
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		return NukkitInventoryUtil.getItemAmountFromInventory(inventory, ((NukkitItemStack) itemStack).getNukkitItem());
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		return freeSpaceAfter(itemStack) >= 0;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return NukkitInventoryUtil.amountFreeSpace(inventory,
				((NukkitItemStack) itemStack).getNukkitItem(), itemStack.getQuantity());
	}

	@Override
	public MCItemStack[] getContents() {
		List<Item> items = new ArrayList<>(inventory.getContents().values());
		MCItemStack[] mcItems = new MCItemStack[items.size()];
		for (int i = 0; i < items.size(); i++){
			mcItems[i] = new NukkitItemStack(items.get(i));
		}

		return mcItems;
	}

	public Inventory getNukkitInventory() {
		return inventory;
	}
}
