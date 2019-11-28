package org.battleplugins.bukkit.inventory.item;

import org.battleplugins.util.MCWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

public class BukkitItemMeta extends MCWrapper<ItemMeta> implements org.battleplugins.inventory.item.ItemMeta {

    private ItemStack itemStack;

    public BukkitItemMeta(ItemStack itemStack, ItemMeta handle) {
        super(handle);

        this.itemStack = itemStack;
    }

    @Override
    public Optional<String> getDisplayName() {
        return Optional.ofNullable(handle.getDisplayName());
    }

    @Override
    public void setDisplayName(String displayName) {
        handle.setDisplayName(displayName);
        itemStack.setItemMeta(handle);
    }

    @Override
    public Optional<List<String>> getLore() {
        return Optional.ofNullable(handle.getLore());
    }

    @Override
    public void setLore(List<String> lore) {
        handle.setLore(lore);
        itemStack.setItemMeta(handle);
    }

    @Override
    public int getCustomModelData() {
        return handle.getCustomModelData();
    }

    @Override
    public void setCustomModelData(int modelData) {
        handle.setCustomModelData(modelData);
        itemStack.setItemMeta(handle);
    }

    @Override
    public boolean isUnbreakable() {
        return handle.isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        handle.setUnbreakable(unbreakable);
        itemStack.setItemMeta(handle);
    }
}
