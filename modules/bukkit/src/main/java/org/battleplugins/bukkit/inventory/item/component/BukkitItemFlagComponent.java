package org.battleplugins.bukkit.inventory.item.component;

import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.ItemFlagComponent;
import org.battleplugins.inventory.item.component.flag.ItemFlag;
import org.battleplugins.inventory.item.component.flag.ItemFlags;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BukkitItemFlagComponent implements ItemFlagComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Set<ItemFlag> itemFlags) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        ItemMeta itemMeta = bukkitItemStack.getItemMeta();
        for (ItemFlag itemFlag : itemFlags) {
            itemMeta.addItemFlags(org.bukkit.inventory.ItemFlag.valueOf(itemFlag.getName().toUpperCase()));
        }
        bukkitItemStack.setItemMeta(itemMeta);
    }

    @Override
    public Optional<Set<ItemFlag>> getValue(ItemStack itemStack) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        Set<ItemFlag> itemFlags = new HashSet<>();
        for (ItemFlag itemFlag : ItemFlags.values()) {
            for (org.bukkit.inventory.ItemFlag bukkitItemFlag : bukkitItemStack.getItemMeta().getItemFlags()) {
                if (bukkitItemFlag.name().equalsIgnoreCase(itemFlag.getName())) {
                    itemFlags.add(itemFlag);
                }
            }
        }

        return itemFlags.isEmpty() ? Optional.empty() : Optional.of(itemFlags);
    }
}
