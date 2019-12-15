package org.battleplugins.bukkit.inventory.item.component;

import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.DisplayNameComponent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class BukkitDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, String displayName) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<String> getValue(ItemStack itemStack) {
        return Optional.ofNullable(((BukkitItemStack) itemStack).getHandle().getItemMeta().getDisplayName());
    }
}
