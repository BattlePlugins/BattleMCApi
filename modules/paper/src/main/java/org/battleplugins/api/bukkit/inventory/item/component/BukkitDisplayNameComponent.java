package org.battleplugins.api.bukkit.inventory.item.component;

import net.kyori.adventure.text.Component;
import org.battleplugins.api.inventory.item.component.DisplayNameComponent;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class BukkitDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Component displayName) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        itemMeta.displayName(displayName);
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<Component> getValue(ItemStack itemStack) {
        return Optional.ofNullable(((BukkitItemStack) itemStack).getHandle().getItemMeta().displayName());
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
