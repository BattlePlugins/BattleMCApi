package org.battleplugins.inventory.entity;

import org.battleplugins.inventory.Inventory;
import org.battleplugins.inventory.item.ItemStack;

import java.util.Optional;

public interface PlayerInventory extends Inventory {

    Optional<? extends ItemStack> getItemInMainHand();
    Optional<? extends ItemStack> getItemInOffHand();

    Optional<? extends ItemStack> getHelmet();
    Optional<? extends ItemStack> getChestplate();
    Optional<? extends ItemStack> getLeggings();
    Optional<? extends ItemStack> getBoots();
}
