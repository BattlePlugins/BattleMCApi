package org.battleplugins.nukkit.inventory.item;

import cn.nukkit.item.Item;

import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.util.NamespacedKey;

public class NukkitItemType extends MCWrapper<Item> implements ItemType {

    protected NamespacedKey key;
    protected Item item;

    NukkitItemType(NamespacedKey key, Item handle) {
        super(handle);

        this.key = key;
    }

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
