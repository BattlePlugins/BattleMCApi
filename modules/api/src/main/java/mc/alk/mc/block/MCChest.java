package mc.alk.mc.block;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

public interface MCChest extends MCBlock {

	boolean isDoubleChest();

	MCItemStack[] getItems();
	MCChest getNeighborChest();
	MCInventory getInventory();
}
