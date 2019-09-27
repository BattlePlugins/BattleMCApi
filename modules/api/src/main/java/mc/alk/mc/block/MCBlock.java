package mc.alk.mc.block;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

public interface MCBlock {

	MCWorld getWorld();
	MCLocation getLocation();

	int getX();
	int getY();
	int getZ();

	String getType();

	void update();
	void update(boolean b);
}
