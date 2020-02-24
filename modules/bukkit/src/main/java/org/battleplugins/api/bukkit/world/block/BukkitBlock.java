package org.battleplugins.api.bukkit.world.block;

import org.battleplugins.api.world.block.BlockRegistry;
import org.battleplugins.api.bukkit.util.BukkitUtil;
import org.battleplugins.api.bukkit.world.BukkitWorld;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.block.BlockType;
import org.bukkit.block.Block;

public class BukkitBlock extends MCWrapper<Block> implements org.battleplugins.api.world.block.Block {

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
		return ((BukkitBlockRegistry) BlockRegistry.get()).fromPlatformBlock(handle.getType());
	}

	@Override
	public BukkitBlock clone() {
		return new BukkitBlock(handle);
	}
}
