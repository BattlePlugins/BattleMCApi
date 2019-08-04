package mc.alk.nukkit;

import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;

import mc.alk.nukkit.block.NukkitBlock;
import mc.alk.nukkit.block.NukkitChest;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCChest;
import mc.alk.mc.block.MCSign;
import mc.alk.nukkit.block.NukkitSign;

public class NukkitWorld implements MCWorld {

	private Level world;

	public NukkitWorld(Level world){
		this.world = world;
	}

	@Override
	public String getName() {
		return world.getName();
	}

	@Override
	public MCBlock getBlockAt(int x, int y, int z) {
		Block b = world.getBlock(x, y, z);
		switch (b.getId()){
			case Block.CHEST:
				return new NukkitChest((BlockEntityChest) world.getBlockEntity(new Vector3(x, y, z)));
			case Block.SIGN_POST:
			case Block.WALL_SIGN:
				return new NukkitSign((BlockEntitySign) world.getBlockEntity(new Vector3(x, y, z)));
			default:
				return new NukkitBlock(b);
		}
	}

	@Override
	public MCBlock getBlockAt(MCLocation loc) {
		return getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	@Override
	public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz) throws ClassCastException{
		if (clazz.isAssignableFrom(block.getClass()))
			return block;
		Location loc = ((NukkitLocation) block.getLocation()).getNukkitLocation();
		Block b = loc.getLevelBlock();
		if (b == null)
			return null;

		if (clazz == MCSign.class){
			if (loc.getLevel().getBlockEntity(loc) instanceof BlockEntitySign)
				return new NukkitSign((BlockEntitySign) loc.getLevel().getBlockEntity(loc));

		} else if (clazz == MCChest.class){
			if (loc.getLevel().getBlockEntity(loc) instanceof BlockEntityChest)
				return new NukkitChest((BlockEntityChest) loc.getLevel().getBlockEntity(loc));

		} else {
			throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
		}
		return null;
	}

	@Override
	public boolean isType(MCBlock block, Class<? extends MCBlock> clazz) {
		try {
			MCBlock b = toType(block,clazz);
			return b != null;
		} catch (ClassCastException e){
			return false;
		}
	}

	@Override
	public String toString(){
		return "[World "+world.getName()+"]";
	}
}
