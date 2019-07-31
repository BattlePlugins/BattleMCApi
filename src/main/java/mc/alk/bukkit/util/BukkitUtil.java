package mc.alk.bukkit.util;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCWorld;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BukkitUtil {

	public static Block getBlock(MCBlock block){
		if (block == null)
			return null;

		MCWorld mcw = block.getWorld();
		if (mcw == null)
			return null;

		World w = Bukkit.getWorld(mcw.getName());
		if (w == null)
			return null;

		return w.getBlockAt(block.getX(), block.getY(), block.getZ());
	}
}
