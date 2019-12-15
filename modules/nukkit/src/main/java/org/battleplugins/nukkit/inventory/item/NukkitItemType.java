package org.battleplugins.nukkit.inventory.item;

import cn.nukkit.item.Item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.util.NamespacedKey;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NukkitItemType implements ItemType {

    protected NamespacedKey key;
    protected Item item;

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public int getMaximumStackSize() {
        return item.getMaxStackSize();
    }

    public Item getHandle() {
        return item;
    }
}
