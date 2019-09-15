package mc.alk.bukkit.block;

import mc.alk.bukkit.BukkitLocation;
import mc.alk.bukkit.BukkitWorld;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.block.Block;

public class BukkitBlock extends MCWrapper<Block> implements MCBlock {

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
	public void update(boolean b) {
		handle.getState().update();
	}

	@Override
	public BukkitBlock clone(){
		return new BukkitBlock(handle);
	}
}
