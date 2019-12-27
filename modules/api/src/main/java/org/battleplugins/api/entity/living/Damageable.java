package org.battleplugins.api.entity.living;

import org.battleplugins.api.entity.Entity;

/**
 * Represents an entity capable of taking damage.
 */
public interface Damageable {

    /**
     * Deals the given amount of damage to
     * the {@link Entity}
     *
     * @param amount the amount of damage to deal
     */
    void damage(double amount);

    /**
     * Deals the given amount of damage to
     * the {@link Entity} from the given {@link Entity} source
     *
     * @param amount the amount of damage to deal
     * @param source the source dealing the damage
     */
    void damage(double amount, Entity source);

    /**
     * The amount of health the {@link Entity} has
     *
     * @return the amount of health the entity has
     */
    double getHealth();

    /**
     * Sets the amount of health the {@link Entity} has
     *
     * @param health the amount of health the entity has
     */
    void setHealth(double health);
}
