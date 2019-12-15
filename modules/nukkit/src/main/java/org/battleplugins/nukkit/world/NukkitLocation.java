package org.battleplugins.nukkit.world;

import cn.nukkit.level.Location;

import org.battleplugins.util.MCWrapper;

public class NukkitLocation extends MCWrapper<Location> implements org.battleplugins.world.Location {

    public NukkitLocation(Location loc){
        super(loc);
    }

    @Override
    public NukkitWorld getWorld() {
        return new NukkitWorld(handle.getLevel());
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
        return (float) handle.getPitch();
    }

    @Override
    public float getYaw() {
        return (float) handle.getYaw();
    }
}
