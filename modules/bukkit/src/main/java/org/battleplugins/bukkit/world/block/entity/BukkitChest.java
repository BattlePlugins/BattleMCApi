package org.battleplugins.bukkit.world.block.entity;

import org.battleplugins.bukkit.inventory.BukkitCarriedInventory;
import org.battleplugins.inventory.CarriedInventory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;

import java.util.Optional;

public class BukkitChest extends BukkitBlockEntity<Chest> implements org.battleplugins.world.block.entity.Chest {

	public BukkitChest(Chest chest) {
		super(chest);
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
	public CarriedInventory<BukkitChest> getInventory() {
		return new BukkitCarriedInventory<>(handle.getInventory(), this);
	}
}
