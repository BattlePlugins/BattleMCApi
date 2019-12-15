package org.battleplugins.sponge.util;

import org.battleplugins.sponge.world.SpongeWorld;
import org.battleplugins.world.Location;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;

import java.util.Collection;

public class SpongeUtil {

    public static Location fromSpongeLocation(org.spongepowered.api.world.Location<World> loc) {
        return new Location(new SpongeWorld(loc.getExtent()),
                loc.getX(),
                loc.getY(),
                loc.getZ());
    }

    public static <T extends Extent> org.spongepowered.api.world.Location<T> toSpongeLocation(Location loc, T extent) {
        return new org.spongepowered.api.world.Location<>(extent,
                loc.getX(),
                loc.getY(),
                loc.getZ());
    }

    public static org.spongepowered.api.world.Location<World> toSpongeLocation(Location loc) {
        return new org.spongepowered.api.world.Location<>(((SpongeWorld) loc.getWorld()).getHandle(),
                loc.getX(),
                loc.getY(),
                loc.getZ());
    }

    public static Collection<Entity> getNearbyEntities(Entity entity, double x, double y, double z) {
        org.spongepowered.api.world.Location<World> location = entity.getLocation();
        return entity.getWorld().getIntersectingEntities(new AABB(location.getX() - x, location.getY() - y, location.getZ() - z, location.getX() + x, location.getY() + y, location.getZ() + z));
    }
}
