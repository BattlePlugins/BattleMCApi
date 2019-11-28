package org.battleplugins.bukkit.world.block;

import org.battleplugins.bukkit.world.BukkitLocation;
import org.battleplugins.bukkit.world.BukkitWorld;
import org.battleplugins.util.MCWrapper;
import org.bukkit.block.Block;

public class BukkitBlock extends MCWrapper<Block> implements org.battleplugins.world.block.Block {

	public BukkitBlock(Block block) {
		super(block);
	}

	@Override
	public BukkitWorld getWorld() {
		return new BukkitWorld(handle.getWorld());
	}

	@Override
	public BukkitLocation getLocation() {
		return new BukkitLocation(handle.getLocation());
	}

	@Override
	public int getX() {
		return handle.getX();
	}

	@Override
	public int getY() {
		return handle.getY();
	}

	@Override
	public int getZ() {
		return handle.getZ();
	}

	@Override
	public String getType() {
		return handle.getType().name();
	}

	@Override
	public BukkitBlock clone() {
		return new BukkitBlock(handle);
	}
}
