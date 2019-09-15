package mc.alk.nukkit.block;

import cn.nukkit.block.Block;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.util.MCWrapper;
import mc.alk.nukkit.NukkitLocation;
import mc.alk.nukkit.NukkitWorld;

public class NukkitBlock extends MCWrapper<Block> implements MCBlock {

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
	public void update(boolean b) {
		handle.onUpdate(1); // Not sure if this works..
	}

	@Override
	public NukkitBlock clone(){
		return new NukkitBlock(handle);
	}
}
