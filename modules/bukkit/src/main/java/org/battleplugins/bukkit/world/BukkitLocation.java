package org.battleplugins.bukkit.world;

import org.battleplugins.util.MCWrapper;
import org.bukkit.Location;

public class BukkitLocation extends MCWrapper<Location> implements org.battleplugins.world.Location {

	public BukkitLocation(Location loc){
		super(loc);
	}

	@Override
	public BukkitWorld getWorld() {
		return new BukkitWorld(handle.getWorld());
	}

	@Override
	public double getX() {
		return handle.getX();
	}

	@Override
	public double getY() {
		return handle.getY();
	}

	@Override
	public double getZ() {
		return handle.getZ();
	}

	@Override
	public float getPitch() {
		return handle.getPitch();
	}

	@Override
	public float getYaw() {
		return handle.getYaw();
	}

	@Override
	public int getBlockX() {
		return handle.getBlockX();
	}

	@Override
	public int getBlockY() {
		return handle.getBlockY();
	}

	@Override
    public int getBlockZ() {
		return handle.getBlockZ();
	}
}
