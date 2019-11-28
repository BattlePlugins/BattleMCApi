package org.battleplugins.sponge.world;

import org.battleplugins.util.MCWrapper;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeLocation extends MCWrapper<Location<World>> implements org.battleplugins.world.Location {

    public SpongeLocation(Location<World> loc){
        super(loc);
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getExtent());
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
        return 0;
    }

    @Override
    public float getYaw() {
        return 0;
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
