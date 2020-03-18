package org.battleplugins.api.event.player;

import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.inventory.item.ItemStack;

import java.util.Optional;

/**
 * An event called when a {@link Player} interacts
 * with an {@link ItemStack} in their hand, or in
 * some cases, just air; {@link #getItem()} will return
 * empty in scenarios like that.
 */
public interface PlayerInteractItemEvent extends PlayerInteractEvent {

    /**
     * The {@link ItemStack} the {@link Player} had in their
     * current {@link Hand} when interacting
     *
     * @return the item the player had in their hand
     */
    Optional<ItemStack> getItem();

    /**
     * The {@link Action} of the event being
     * ran on the {@link ItemStack}
     *
     * @return the action of the event being ran on the item stack
     */
    Action getAction();

    enum Action {

        /**
         * When a player is using their primary
         * click (usually left-clicking)
         */
        PRIMARY,

        /**
         * When a player is using their secondary
         * click (usually right-clicking)
         */
        SECONDARY,
    }
}
