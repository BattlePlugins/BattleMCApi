package org.battleplugins.nukkit.util;

import org.battleplugins.nukkit.world.NukkitWorld;
import org.battleplugins.world.Location;

public class NukkitUtil {

    public static Location fromNukkitLocation(cn.nukkit.level.Location loc) {
        return new Location(new NukkitWorld(loc.getLevel()),
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                (float) loc.getPitch(),
                (float) loc.getYaw());
    }

    public static cn.nukkit.level.Location toNukkitLocation(Location loc) {
        return new cn.nukkit.level.Location(loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getYaw(),
                loc.getPitch(),
                ((NukkitWorld) loc.getWorld()).getHandle());
    }
}
