package org.battleplugins.api.nukkit.inventory.item;

import cn.nukkit.item.Item;

import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.NamespacedKey;

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
