package org.battleplugins.world;

public interface Location {

	World getWorld();

	double getX();
	double getY();
	double getZ();

	float getPitch();
	float getYaw();

	int getBlockX();
	int getBlockY();
	int getBlockZ();
}
