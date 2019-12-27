package org.battleplugins.api.nukkit.util;

import org.battleplugins.api.world.Location;
import org.battleplugins.api.nukkit.world.NukkitWorld;

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
