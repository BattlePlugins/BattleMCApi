package mc.alk.mc;

import mc.alk.mc.block.MCBlock;

public interface MCWorld {

	String getName();

	MCBlock getBlockAt(int x, int y, int z);
	MCBlock getBlockAt(MCLocation loc);

	boolean isType(MCBlock block, Class<? extends MCBlock> clazz);
	<T extends MCBlock> T toType(MCBlock block, Class<T> clazz) throws ClassCastException;
}
