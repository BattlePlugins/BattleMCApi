package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.event.Cancellable;

/**
 * An event called when a {@link Player} interacts
 * with the world, whether it be with a
 * block or an entity.
 */
public interface PlayerInteractEvent extends PlayerEvent, Cancellable {

    /**
     * The {@link Hand} the {@link Player} had
     * selected when interacting
     *
     * @return the selected hand the player had when interacting
     */
    Hand getHand();
}
