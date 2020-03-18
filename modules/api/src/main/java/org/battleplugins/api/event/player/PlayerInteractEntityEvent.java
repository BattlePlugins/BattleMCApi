package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.living.player.Player;

/**
 * An event called when a {@link Player} interacts
 * with an {@link Entity}.
 */
public interface PlayerInteractEntityEvent extends PlayerInteractEvent {

    /**
     * The {@link Entity} the {@link Player}
     * interacted with
     *
     * @return the entity the player interacted with
     */
    Entity getEntity();

    /**
     * The {@link Action} of the event being
     * ran on the {@link Entity}
     *
     * @return the action of the event being ran on the entity
     */
    Action getAction();

    enum Action {

        /**
         * When a player is attacking an entity (usually
         * left-clicking)
         */
        ATTACK,

        /**
         * When a player is interacting with an
         * entity (usually right-clicking)
         */
        INTERACT,
    }
}
