package mc.alk.nukkit.inventory;

import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.util.MCWrapper;
import mc.alk.nukkit.util.NukkitInventoryUtil;

import java.util.ArrayList;
import java.util.List;

public class NukkitInventory extends MCWrapper<Inventory> implements MCInventory {

	public NukkitInventory(Inventory inventory) {
		super(inventory);
	}

	@Override
	public void addItem(MCItemStack... itemStacks) {
		for (MCItemStack item: itemStacks)
			addItem(item);
	}

	public void addItem(MCItemStack itemStack) {
		if (itemStack == null || itemStack.getType().equals("0")) {
			return;
		}

		NukkitInventoryUtil.addItemToInventory(handle, ((NukkitItemStack)itemStack).getHandle(),itemStack.getQuantity());
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		handle.removeItem(((NukkitItemStack) itemStack).getHandle());
	}

	@Override
	public void setItem(int slot, MCItemStack item) { handle.setItem(slot, ((NukkitItemStack) item).getHandle());
	}

	@Override
	public NukkitItemStack getItem(int slot) {
		return new NukkitItemStack(handle.getItem(slot));
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		return NukkitInventoryUtil.getItemAmountFromInventory(handle, ((NukkitItemStack) itemStack).getHandle());
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		return freeSpaceAfter(itemStack) >= 0;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return NukkitInventoryUtil.amountFreeSpace(handle,
				((NukkitItemStack) itemStack).getHandle(), itemStack.getQuantity());
	}

	@Override
	public NukkitItemStack[] getContents() {
		List<Item> items = new ArrayList<>(handle.getContents().values());
		NukkitItemStack[] mcItems = new NukkitItemStack[items.size()];
		for (int i = 0; i < items.size(); i++){
			mcItems[i] = new NukkitItemStack(items.get(i));
		}

		return mcItems;
	}

	@Override
	public void clear() {
		handle.clearAll();
	}
}
