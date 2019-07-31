package mc.alk.bukkit.block;

import mc.alk.bukkit.BukkitLocation;
import mc.alk.bukkit.BukkitWorld;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.bukkit.block.Block;

public class BukkitBlock implements MCBlock {

	private Block block;

	public BukkitBlock(Block block) {
		this.block = block;
	}

	@Override
	public MCWorld getWorld() {
		return new BukkitWorld(block.getWorld());
	}

	@Override
	public MCLocation getLocation() {
		return new BukkitLocation(block.getLocation());
	}

	@Override
	public int getX() {
		return block.getX();
	}

	@Override
	public int getY() {
		return block.getY();
	}

	@Override
	public int getZ() {
		return block.getZ();
	}

	@Override
	public String getType() {
		return block.getType().name();
	}

	@Override
	public void update(boolean b) {
		block.getState().update();
	}

	@Override
	public BukkitBlock clone(){
		return new BukkitBlock(block);
	}

	public Block getBukkitBlock() {
		return block;
	}

}
