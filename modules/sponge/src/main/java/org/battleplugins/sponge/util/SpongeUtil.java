package org.battleplugins.sponge.util;

import com.google.common.base.Preconditions;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Collection;

public class SpongeUtil {

    public static Collection<Entity> getNearbyEntities(Entity entity, double x, double y, double z) {
        Location<World> location = entity.getLocation();
        Preconditions.checkNotNull(location, "location");
        Preconditions.checkArgument(x > 0.0D, "x must be > 0");
        Preconditions.checkArgument(y > 0.0D, "y must be > 0");
        Preconditions.checkArgument(y > 0.0D, "z must be > 0");
        return entity.getWorld().getIntersectingEntities(new AABB(location.getX() - x, location.getY() - y, location.getZ() - z, location.getX() + x, location.getY() + y, location.getZ() + z));
    }
}
