package mc.alk.nukkit.block;

import cn.nukkit.block.Block;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.nukkit.NukkitLocation;
import mc.alk.nukkit.NukkitWorld;

public class NukkitBlock implements MCBlock {

	private Block block;

	public NukkitBlock(Block block) {
		this.block = block;
	}

	@Override
	public MCWorld getWorld() {
		return new NukkitWorld(block.getLevel());
	}

	@Override
	public MCLocation getLocation() {
		return new NukkitLocation(block.getLocation());
	}

	@Override
	public int getX() {
		return (int) block.x;
	}

	@Override
	public int getY() {
		return (int) block.y;
	}

	@Override
	public int getZ() {
		return (int) block.z;
	}

	@Override
	public String getType() {
		return String.valueOf(block.getId());
	}

	@Override
	public void update(boolean b) {
		block.onUpdate(1); // Not sure if this works..
	}

	@Override
	public NukkitBlock clone(){
		return new NukkitBlock(block);
	}

	public Block getNukkitBlock() {
		return block;
	}

}
