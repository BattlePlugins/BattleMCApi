package org.battleplugins.world.block.entity;

import org.battleplugins.inventory.Inventory;
import org.battleplugins.inventory.item.ItemStack;

import java.util.Optional;

public interface Chest extends BlockEntity {

	boolean isDoubleChest();

	Optional<? extends Chest> getNeighborChest();

	ItemStack[] getItems();
	Inventory getInventory();
}
