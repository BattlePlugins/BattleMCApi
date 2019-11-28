package org.battleplugins.inventory;

import org.battleplugins.inventory.item.ItemStack;

import java.util.Optional;

public interface Inventory {

	void addItem(ItemStack...itemStacks);
	void removeItem(ItemStack itemStack);

	void setItem(int slot, ItemStack item);
	Optional<? extends ItemStack> getItem(int slot);

	int getItemAmount(ItemStack itemStack);
	int freeSpaceAfter(ItemStack itemStack);

	boolean canFit(ItemStack itemStack);

	ItemStack[] getContents();

	default void setContents(ItemStack[] contents) {
		for (int i = 0; i < contents.length; i++) {
			setItem(i, contents[i]);
		}
	}

	void clear();
}
