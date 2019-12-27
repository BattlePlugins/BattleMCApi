package org.battleplugins.api.world.block.entity;

import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.carrier.block.entity.BlockEntityCarrier;

import java.util.Optional;

/**
 * Represents a chest.
 */
public interface Chest extends BlockEntity, BlockEntityCarrier<Chest> {

	/**
	 * If the chest is a double chest
	 *
	 * @return if the chest is a double chest
	 */
	default boolean isDoubleChest() {
		return getNeighborChest().isPresent();
	}

	/**
	 * The neighbor chest next to this one. Will
	 * return empty if there is none
	 *
	 * @return the neighbor chest to this one
	 */
	Optional<? extends Chest> getNeighborChest();

	/**
	 * The contents of the chest {@link Inventory}
	 *
	 * @return the contents of the chest inventory
	 */
	default ItemStack[] getContents() {
		return getInventory().getContents();
	}
}
