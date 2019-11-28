package org.battleplugins.bukkit.world.block.entity;

import org.battleplugins.bukkit.inventory.BukkitInventory;
import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class BukkitChest extends BukkitBlockEntity<Chest> implements org.battleplugins.world.block.entity.Chest {

	public BukkitChest(Chest chest) {
		super(chest);
	}

	@Override
	public BukkitItemStack[] getItems() {
		ItemStack[] items1 = handle.getInventory().getContents();
		BukkitItemStack[] items2 = new BukkitItemStack[items1.length];
		for (int i = 0; i < items1.length; i++){
			items2[i] = new BukkitItemStack(items1[i]);
		}
		return items2;
	}

	@Override
	public boolean isDoubleChest() {
		final Block b = handle.getBlock();
		return (b.getRelative(BlockFace.NORTH).getType() == Material.CHEST ||
				b.getRelative(BlockFace.SOUTH).getType() == Material.CHEST ||
				b.getRelative(BlockFace.EAST).getType() == Material.CHEST ||
				b.getRelative(BlockFace.WEST).getType() == Material.CHEST);
	}

	@Override
	public Optional<BukkitChest> getNeighborChest() {
		if (handle.getBlock().getRelative(BlockFace.NORTH).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getBlock().getRelative(BlockFace.NORTH).getState()));

		else if (handle.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getBlock().getRelative(BlockFace.SOUTH).getState()));

		else if (handle.getBlock().getRelative(BlockFace.EAST).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getBlock().getRelative(BlockFace.EAST).getState()));

		else if (handle.getBlock().getRelative(BlockFace.WEST).getType() == Material.CHEST)
			return Optional.of(new BukkitChest((Chest) handle.getBlock().getRelative(BlockFace.WEST).getState()));

		return Optional.empty();
	}

	@Override
	public BukkitInventory getInventory() {
		return new BukkitInventory<>(handle.getInventory());
	}
}
