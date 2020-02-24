package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.event.Event;

/**
 * Represents an event relating to players.
 */
public interface PlayerEvent extends Event {

    /**
     * The player for this event
     *
     * @return the player for this event
     */
    Player getPlayer();
}
