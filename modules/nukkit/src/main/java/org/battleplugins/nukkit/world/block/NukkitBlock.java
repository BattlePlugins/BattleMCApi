package org.battleplugins.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.nukkit.world.NukkitLocation;
import org.battleplugins.nukkit.world.NukkitWorld;
import org.battleplugins.util.MCWrapper;

public class NukkitBlock extends MCWrapper<Block> implements org.battleplugins.world.block.Block {

	public NukkitBlock(Block block) {
		super(block);
	}

	@Override
	public NukkitWorld getWorld() {
		return new NukkitWorld(handle.getLevel());
	}

	@Override
	public NukkitLocation getLocation() {
		return new NukkitLocation(handle.getLocation());
	}

	@Override
	public int getX() {
		return (int) handle.x;
	}

	@Override
	public int getY() {
		return (int) handle.y;
	}

	@Override
	public int getZ() {
		return (int) handle.z;
	}

	@Override
	public String getType() {
		return String.valueOf(handle.getId());
	}

	@Override
	public NukkitBlock clone(){
		return new NukkitBlock(handle);
	}
}
