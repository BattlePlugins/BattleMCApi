package mc.alk.mc.entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MCEntity {

    String getName();
    UUID getUniqueId();

    MCLocation getLocation();
    MCWorld getWorld();

    boolean teleport(MCLocation location);

    default boolean teleport(MCEntity entity) {
        return teleport(entity.getLocation());
    }

    boolean isDead();
    boolean isValid();

    List<? extends MCEntity> getNearbyEntities(double x, double y, double z);

    default boolean hasCustomName() {
        return getCustomName().isPresent();
    }

    boolean isCustomNameVisible();
    Optional<String> getCustomName();

    void setCustomName(String customName);
    void setCustomNameVisible(boolean visible);
}
