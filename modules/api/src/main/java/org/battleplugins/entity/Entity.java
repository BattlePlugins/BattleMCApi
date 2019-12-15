package org.battleplugins.entity;

import org.battleplugins.util.NamespacedKey;
import org.battleplugins.world.Location;
import org.battleplugins.world.World;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents an entity in a world.
 */
public interface Entity {

    /**
     * The name of the entity
     *
     * @return the name of the entity
     */
    String getName();

    /**
     * The identifier of this entity
     *
     * @return the identifier of this entity
     */
    default String getIdentifier() {
        return getKey().toString();
    }

    /**
     * The full {@link NamespacedKey} of this entity
     *
     * @return the full namespaced key of this entity
     */
    NamespacedKey getKey();

    /**
     * The {@link UUID} of the entity
     *
     * @return the unique identifier of the entity
     */
    UUID getUniqueId();

    /**
     * The {@link Location} of the entity
     *
     * @return the location of the entity
     */
    Location getLocation();

    /**
     * The {@link World} the entity is located in
     *
     * @return the world the entity is located in
     */
    World getWorld();

    /**
     * Teleports the entity to the given {@link Location}
     *
     * @param location the location to teleport the entity to
     * @return if the entity successfully teleported
     */
    boolean teleport(Location location);

    /**
     * Teleports the entity to the {@link Location} of another entity
     *
     * @param entity the entity to teleport to
     * @return if the entity successfully teleported
     */
    default boolean teleport(Entity entity) {
        return teleport(entity.getLocation());
    }

    /**
     * If the entity is dead
     *
     * @return if the entity is dead
     */
    boolean isDead();

    /**
     * If the entity is valid
     *
     * @return if the entity is valid
     */
    boolean isValid();

    /**
     * A list of all the nearby entities
     *
     * @param x the radius to search in the x axis
     * @param y the radius to search in the y axis
     * @param z the radius to search in the z axis
     * @return a list of all the nearby entities
     */
    List<? extends Entity> getNearbyEntities(double x, double y, double z);

    /**
     * If the entity has a custom name
     *
     * @return if the entity has a custom name
     */
    default boolean hasCustomName() {
        return getCustomName().isPresent();
    }

    /**
     * If the entity's custom name is visible
     *
     * @return if the entity's custom name is visible
     */
    boolean isCustomNameVisible();

    /**
     * The entity's custom name
     *
     * @return the entity's custom name
     */
    Optional<String> getCustomName();

    /**
     * Sets the custom name of the entity
     *
     * @param customName the custom name to give to the entity
     */
    void setCustomName(String customName);

    /**
     * Sets if the custom name of the entity (if applicable) should be visible
     *
     * @param visible if the custom name of the entity should be visible
     */
    void setCustomNameVisible(boolean visible);
}
