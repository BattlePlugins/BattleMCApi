package org.battleplugins.api.bukkit.inventory.item.component;

import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.CustomModelDataComponent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class BukkitCustomModelDataComponent implements CustomModelDataComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Integer modelData) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        try {
            itemMeta.setCustomModelData(modelData);
        } catch (Throwable ex) {
            // May not be supported in this version
        }
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<Integer> getValue(ItemStack itemStack) {
        try {
            return Optional.of(((BukkitItemStack) itemStack).getHandle().getItemMeta().getCustomModelData());
        } catch (Throwable ex) {
            // May not be supported in this version
        }

        return Optional.empty();
    }
}
