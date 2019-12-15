package org.battleplugins.bukkit.world.block;

import org.battleplugins.bukkit.util.BukkitUtil;
import org.battleplugins.bukkit.world.BukkitWorld;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.Location;
import org.battleplugins.world.block.BlockRegistry;
import org.battleplugins.world.block.BlockType;
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
	public Location getLocation() {
		return BukkitUtil.fromBukkitLocation(handle.getLocation());
	}

	@Override
	public BlockType getType() {
		return ((BukkitBlockRegistry) BlockRegistry.REGISTRY).fromPlatformBlock(handle.getType());
	}

	@Override
	public BukkitBlock clone() {
		return new BukkitBlock(handle);
	}
}
