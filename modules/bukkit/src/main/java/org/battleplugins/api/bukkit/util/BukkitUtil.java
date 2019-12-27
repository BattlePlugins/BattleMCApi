package org.battleplugins.api.bukkit.util;

import org.battleplugins.api.bukkit.world.BukkitWorld;
import org.battleplugins.api.world.Location;

public class BukkitUtil {

    public static Location fromBukkitLocation(org.bukkit.Location loc) {
        return new Location(new BukkitWorld(loc.getWorld()),
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getPitch(),
                loc.getYaw());
    }

    public static org.bukkit.Location toBukkitLocation(Location loc) {
        return new org.bukkit.Location(((BukkitWorld) loc.getWorld()).getHandle(),
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getPitch(),
                loc.getYaw());
    }
}
