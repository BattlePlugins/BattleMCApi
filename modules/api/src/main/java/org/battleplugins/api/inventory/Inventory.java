package org.battleplugins.api.inventory;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.plugin.Plugin;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents an ingame menu container.
 */
public interface Inventory {

	/**
	 * Adds the given {@link ItemStack} to the inventory
	 *
	 * @param itemStack the itemstack to add
	 */
	void addItem(ItemStack itemStack);

	/**
	 * Adds the given {@link ItemStack}s to the inventory
	 *
	 * @param itemStacks the itemstacks to add
	 */
	default void addItems(ItemStack... itemStacks) {
		Arrays.asList(itemStacks).forEach(this::addItem);
	}

	/**
	 * Removes the given {@link ItemStack} from the inventory
	 *
	 * @param itemStack the itemstack to remove
	 */
	void removeItem(ItemStack itemStack);

	/**
	 * Sets an {@link ItemStack} at the given slot
	 *
	 * @param slot the slot to set the item at
	 * @param item the itemstack to set
	 */
	void setItem(int slot, ItemStack item);

	/**
	 * Gets an {@link ItemStack} at the given slot. Will not be
	 * present if the given slot is out of range
	 * or there is no item there
	 *
	 * @param slot the slot to get the item from
	 * @return an item at the given slot
	 */
	Optional<? extends ItemStack> getItem(int slot);

	/**
	 * Gets the amount of a specified {@link ItemStack}
	 * throughout the whole inventory
	 *
	 * @param itemStack the itemstack to get the amount of
	 * @return the amount of an itemstack in the inventory
	 */
	int getItemAmount(ItemStack itemStack);

	/**
	 * Gets the amount of free space after
	 * the given {@link ItemStack}
	 *
	 * @param itemStack the itemstack to get the free space after
	 * @return the amount of free space after the given itemstack
	 */
	int freeSpaceAfter(ItemStack itemStack);

	/**
	 * Gets if the given {@link ItemStack} can fit in
	 * the inventory
	 *
	 * @param itemStack the itemstack to check
	 * @return if the given itemstack can fit
	 */
	default boolean canFit(ItemStack itemStack) {
		return freeSpaceAfter(itemStack) >= 0;
	}

	/**
	 * The contents of the inventory
	 *
	 * @return the contents of the inventory
	 */
	ItemStack[] getContents();

	/**
	 * Sets the contents of the inventory
	 *
	 * @param contents the contents to set
	 */
	default void setContents(ItemStack[] contents) {
		for (int i = 0; i < contents.length; i++) {
			setItem(i, contents[i]);
		}
	}

	/**
	 * Clears the inventory
	 */
	void clear();

	/**
	 * The inventory builder
	 *
	 * @return a new inventory builder
	 */
	static Builder builder() {
		return Platform.getPlatform().getRegistry().getBuilder(Builder.class);
	}

	interface Builder {

		Builder fromInventory(Inventory inventory, String name);

		Builder name(String name);

		Builder size(int size);

		Builder item(int slot, ItemStack item);

		Builder contents(ItemStack[] contents);

		Inventory build(Plugin plugin);
	}
}
