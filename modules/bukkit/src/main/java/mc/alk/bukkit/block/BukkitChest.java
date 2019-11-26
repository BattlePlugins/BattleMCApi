package mc.alk.bukkit.block;

import mc.alk.bukkit.inventory.BukkitInventory;
import mc.alk.bukkit.inventory.BukkitItemStack;
import mc.alk.mc.block.MCChest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class BukkitChest extends BukkitBlock implements MCChest {

	private Chest chest;

	public BukkitChest(Chest chest) {
		super(chest.getBlock());

		this.chest = chest;
	}

	@Override
	public BukkitItemStack[] getItems() {
		ItemStack[] items1 = chest.getInventory().getContents();
		BukkitItemStack[] items2 = new BukkitItemStack[items1.length];
		for (int i = 0; i < items1.length; i++){
			items2[i] = new BukkitItemStack(items1[i]);
		}
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
	public Optional<BukkitChest> getNeighborChest() {
		if (handle.getRelative(BlockFace.NORTH).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getRelative(BlockFace.NORTH).getState()));

		else if (handle.getRelative(BlockFace.SOUTH).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getRelative(BlockFace.SOUTH).getState()));

		else if (handle.getRelative(BlockFace.EAST).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getRelative(BlockFace.EAST).getState()));

		else if (handle.getRelative(BlockFace.WEST).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getRelative(BlockFace.WEST).getState()));

		return Optional.empty();
	}

	@Override
	public BukkitInventory getInventory() {
		return new BukkitInventory(chest.getInventory());
	}
}
