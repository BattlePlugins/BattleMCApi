package mc.alk.mc.block;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

import java.util.Optional;

public interface MCChest extends MCBlock {

	boolean isDoubleChest();

	Optional<? extends MCChest> getNeighborChest();

	MCItemStack[] getItems();
	MCInventory getInventory();
}
