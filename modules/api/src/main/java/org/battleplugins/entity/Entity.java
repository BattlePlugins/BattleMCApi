package org.battleplugins.entity;

import org.battleplugins.world.Location;
import org.battleplugins.world.World;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Entity {

    String getName();
    UUID getUniqueId();

    Location getLocation();
    World getWorld();

    boolean teleport(Location location);

    default boolean teleport(Entity entity) {
        return teleport(entity.getLocation());
    }

    boolean isDead();
    boolean isValid();

    List<? extends Entity> getNearbyEntities(double x, double y, double z);

    default boolean hasCustomName() {
        return getCustomName().isPresent();
    }

    boolean isCustomNameVisible();
    Optional<String> getCustomName();

    void setCustomName(String customName);
    void setCustomNameVisible(boolean visible);
}
