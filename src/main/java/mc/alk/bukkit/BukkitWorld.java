package mc.alk.bukkit;

import mc.alk.bukkit.block.BukkitBlock;
import mc.alk.bukkit.block.BukkitChest;
import mc.alk.bukkit.block.BukkitSign;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCChest;
import mc.alk.mc.block.MCSign;
import mc.euro.bukkitadapter.material.BattleMaterial;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;

public class BukkitWorld implements MCWorld {

	private World world;

	public BukkitWorld(World world){
		this.world = world;
	}

	@Override
	public String getName() {
		return world.getName();
	}

	@Override
	public MCBlock getBlockAt(int x, int y, int z) {
		Block b = world.getBlockAt(x, y, z);
		BattleMaterial type = BattleMaterial.fromMaterial(b.getType());
		switch (type){
			case CHEST:
				return new BukkitChest((Chest) b.getState());
			case OAK_SIGN:
			case SPRUCE_SIGN:
			case BIRCH_SIGN:
			case JUNGLE_SIGN:
			case ACACIA_SIGN:
			case DARK_OAK_SIGN:
			case OAK_WALL_SIGN:
			case SPRUCE_WALL_SIGN:
			case BIRCH_WALL_SIGN:
			case JUNGLE_WALL_SIGN:
			case ACACIA_WALL_SIGN:
			case DARK_OAK_WALL_SIGN:
				return new BukkitSign((Sign) b.getState());
			default:
				return new BukkitBlock(b);
		}
	}

	@Override
	public MCBlock getBlockAt(MCLocation loc) {
		return getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
	}

	@Override
	public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz) throws ClassCastException{
		if (clazz.isAssignableFrom(block.getClass()))
			return block;
		Location loc = ((BukkitLocation) block.getLocation()).getBukkitLocation();
		Block b = loc.getBlock();
		if (b == null)
			return null;

		if (clazz == MCSign.class){
			if (b.getState() instanceof Sign)
				return new BukkitSign((Sign)b.getState());

		} else if (clazz == MCChest.class){
			if (b.getState() instanceof Chest)
				return new BukkitChest((Chest) b.getState());

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
