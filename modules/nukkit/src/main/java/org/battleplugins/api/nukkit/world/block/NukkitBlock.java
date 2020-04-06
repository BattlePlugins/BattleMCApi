package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.api.nukkit.util.NukkitUtil;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.block.BlockType;
import org.battleplugins.api.nukkit.world.NukkitWorld;

public class NukkitBlock extends MCWrapper<Block> implements org.battleplugins.api.world.block.Block {

	public NukkitBlock(Block block) {
		super(block);
	}

	@Override
	public NukkitWorld getWorld() {
		return new NukkitWorld(handle.getLevel());
	}

	@Override
	public Location getLocation() {
		return NukkitUtil.fromNukkitLocation(handle.getLocation());
	}

	@Override
	public BlockType getType() {
		return new NukkitBlockType(handle);
	}

	@Override
	public NukkitBlock clone(){
		return new NukkitBlock(handle);
	}
}
