package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.event.Event;

/**
 * Represents an event relating to {@link Player}s.
 */
public interface PlayerEvent extends Event {

    /**
     * The {@link Player} for this event
     *
     * @return the player for this event
     */
    Player getPlayer();
}
