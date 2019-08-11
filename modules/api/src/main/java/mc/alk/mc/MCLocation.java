package mc.alk.mc;

public interface MCLocation {

	MCWorld getWorld();

	double getX();
	double getY();
	double getZ();

	float getPitch();
	float getYaw();

	int getBlockX();
	int getBlockY();
	int getBlockZ();
}
