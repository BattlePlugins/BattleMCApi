package org.battleplugins.api.bukkit.inventory.item.component;

import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.LoreComponent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

public class BukkitLoreComponent implements LoreComponent {

    @Override
    public void applyComponent(ItemStack itemStack, List<String> lore) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        itemMeta.setLore(lore);
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<List<String>> getValue(ItemStack itemStack) {
        return Optional.ofNullable(((BukkitItemStack) itemStack).getHandle().getItemMeta().getLore());
    }
}
