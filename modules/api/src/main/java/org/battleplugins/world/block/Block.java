package org.battleplugins.world.block;

import org.battleplugins.world.Location;
import org.battleplugins.world.World;

public interface Block {

	World getWorld();
	Location getLocation();

	int getX();
	int getY();
	int getZ();

	String getType();
}
