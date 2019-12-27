package org.battleplugins.api.bukkit.inventory.item.component;

import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.UnbreakableComponent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class BukkitUnbreakableComponent implements UnbreakableComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Boolean unbreakable) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        itemMeta.setUnbreakable(unbreakable);
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<Boolean> getValue(ItemStack itemStack) {
        ItemMeta itemMeta = ((BukkitItemStack) itemStack).getHandle().getItemMeta();
        return Optional.of(itemMeta.isUnbreakable());
    }
}
