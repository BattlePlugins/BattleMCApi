package org.battleplugins.api.inventory.entity;

import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.hand.Hands;
import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.inventory.CarriedInventory;
import org.battleplugins.api.inventory.item.ItemStack;

import java.util.Optional;

/**
 * Represents an inventory for a player.
 */
public interface PlayerInventory extends CarriedInventory<Human> {

    default Optional<ItemStack> getItemInHand(Hand hand) {
        if (hand == Hands.MAIN_HAND) {
            return getItemInMainHand();
        }
        if (hand == Hands.OFF_HAND) {
            return getItemInOffHand();
        }
        return Optional.empty();
    }

    /**
     * Gets the item in the main hand of the {@link Player}
     *
     * @return the item in the main hand of the player
     */
    Optional<ItemStack> getItemInMainHand();

    /**
     * Gets the item in the offhand of the {@link Player}
     *
     * @return the item in the offhand of the player
     */
    Optional<ItemStack> getItemInOffHand();

    /**
     * Gets the item in the helmet slot of the {@link Player}
     *
     * @return the item in the helmet slot of the player
     */
    Optional<ItemStack> getHelmet();

    /**
     * Gets the item in the chestplate slot of the {@link Player}
     *
     * @return the item in the chestplate slot of the player
     */
    Optional<ItemStack> getChestplate();

    /**
     * Gets the item in the leggings slot of the {@link Player}
     *
     * @return the item in the leggings slot of the player
     */
    Optional<ItemStack> getLeggings();

    /**
     * Gets the item in the boots slot of the {@link Player}
     *
     * @return the item in the boots slot of the player
     */
    Optional<ItemStack> getBoots();
}
