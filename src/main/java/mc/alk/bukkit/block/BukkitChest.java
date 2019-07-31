package mc.alk.bukkit.block;

import mc.alk.bukkit.inventory.BukkitInventory;
import mc.alk.bukkit.inventory.BukkitItemStack;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.block.MCChest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

public class BukkitChest extends BukkitBlock implements MCChest{

	private Chest chest;

	public BukkitChest(Chest chest) {
		super(chest.getBlock());
		this.chest = chest;
	}

	@Override
	public MCItemStack[] getItems() {
		ItemStack[] items1 = chest.getInventory().getContents();
		MCItemStack[] items2 = new MCItemStack[items1.length];
		for (int i=0;i<items1.length;i++){
			items2[i] = new BukkitItemStack(items1[i]);}
		return items2;
	}

	@Override
	public boolean isDoubleChest() {
		final Block b = chest.getBlock();
		return (b.getRelative(BlockFace.NORTH).getType() == Material.CHEST ||
				b.getRelative(BlockFace.SOUTH).getType() == Material.CHEST ||
				b.getRelative(BlockFace.EAST).getType() == Material.CHEST ||
				b.getRelative(BlockFace.WEST).getType() == Material.CHEST);
	}

	@Override
	public MCChest getNeighborChest() {
		return getNeighborChest(this);
	}

	public static MCChest getNeighborChest(MCBlock block) {
		Block b = ((BukkitBlock)block).getBukkitBlock();
		if (b.getRelative(BlockFace.NORTH).getType() == Material.CHEST)
			return new BukkitChest((Chest) b.getRelative(BlockFace.NORTH).getState());
		else if (b.getRelative(BlockFace.SOUTH).getType() == Material.CHEST)
			return new BukkitChest((Chest) b.getRelative(BlockFace.SOUTH).getState());
		else if (b.getRelative(BlockFace.EAST).getType() == Material.CHEST)
			return new BukkitChest((Chest) b.getRelative(BlockFace.EAST).getState());
		else if (b.getRelative(BlockFace.WEST).getType() == Material.CHEST)
			return new BukkitChest((Chest) b.getRelative(BlockFace.WEST).getState());
		return null;
	}

	@Override
	public MCInventory getInventory() {
		return new BukkitInventory(chest.getInventory());
	}
}
