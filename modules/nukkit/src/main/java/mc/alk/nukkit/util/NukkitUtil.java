package mc.alk.nukkit.util;

import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCWorld;

public class NukkitUtil {

	public static Block getBlock(MCBlock block){
		if (block == null)
			return null;

		MCWorld mcw = block.getWorld();
		if (mcw == null)
			return null;

		Level w = Server.getInstance().getLevelByName(mcw.getName());
		if (w == null)
			return null;

		return w.getBlock(block.getX(), block.getY(), block.getZ());
	}
}
