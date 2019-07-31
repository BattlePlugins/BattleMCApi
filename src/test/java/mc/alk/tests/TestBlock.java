package mc.alk.tests;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

public class TestBlock implements MCBlock{
	int type;
	short value;
	MCLocation loc;

	public TestBlock(MCLocation loc, int id) {
		this.loc = loc;
		this.type = id;
		if (loc.getWorld() instanceof TestWorld){
			((TestWorld)loc.getWorld()).addBlock(this);}
	}

	@Override
	public MCWorld getWorld() {
		return loc.getWorld();
	}
	@Override
	public MCLocation getLocation() {
		return loc;
	}
	@Override
	public int getX() {
		return loc.getBlockX();
	}
	@Override
	public int getY() {
		return loc.getBlockY();
	}
	@Override
	public int getZ() {
		return loc.getBlockZ();
	}
	@Override
	public int getType() {
		return type;
	}
	@Override
	public String toString(){
		return "[Block " + type +":"+ value +" " + loc+"]";
	}

	@Override
	public void update(boolean b) {
		/* do nothing */
	}
}
