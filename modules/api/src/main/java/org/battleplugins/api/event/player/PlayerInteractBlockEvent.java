package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.world.block.Block;

/**
 * An event called when a {@link Player} interacts
 * with a {@link Block}.
 */
public interface PlayerInteractBlockEvent extends PlayerInteractEvent {

    /**
     * The {@link Block} the {@link Player}
     * interacted with
     *
     * @return the block the player interacted with
     */
    Block getBlock();

    /**
     * The {@link Action} of the event being
     * ran on the {@link Block}
     *
     * @return the action of the event being ran on the block
     */
    Action getAction();

    enum Action {

        /**
         * When a player is breaking (usually left-clicking)
         * a block
         */
        BREAK,

        /**
         * When a player is placing (usually right-clicking)
         * a block
         */
        PLACE,
    }
}
