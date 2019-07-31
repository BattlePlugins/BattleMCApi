package mc.alk.tests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

public class TestWorld implements MCWorld{
	String name;

	Map<String,MCBlock> blocks = new ConcurrentHashMap<String,MCBlock>();

	public TestWorld(String name){
		this.name = name;
		TestServer.setWorld(name,this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MCBlock getBlockAt(int x, int y, int z) {
		String key = toKey(x,y,z);
		if (blocks.containsKey(key))
			return blocks.get(key);
		else {
			return new TestBlock(new TestLocation(this,x,y,z),0);
		}
	}

	@Override
	public MCBlock getBlockAt(MCLocation loc) {
		return getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	public static String toKey(int x, int y, int z){
		return x+":"+y+":"+z;
	}

	private String toKey(MCLocation location) {
		return toKey(location.getBlockX(),location.getBlockY(), location.getBlockZ());
	}

	public MCBlock addBlock(MCBlock block) {
		return blocks.put(toKey(block.getLocation()), block);
	}

	@Override
	public String toString(){
		return "["+name+":"+hashCode()+"]";
	}

	@Override
	public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz) throws ClassCastException {
		if (clazz.isAssignableFrom(block.getClass()))
			return block;
		throw new ClassCastException("block " + block +" cannot be cast to " + clazz);
	}

	@Override
	public boolean isType(MCBlock block, Class<? extends MCBlock> clazz) {
		try{MCBlock b = toType(block,clazz);
			return b != null;
		} catch (ClassCastException e){
			return false;
		}
	}

}
