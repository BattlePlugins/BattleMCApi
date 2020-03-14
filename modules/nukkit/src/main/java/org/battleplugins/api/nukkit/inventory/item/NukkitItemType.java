package org.battleplugins.api.nukkit.inventory.item;

import cn.nukkit.item.Item;

import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.inventory.item.ItemType;

public class NukkitItemType extends MCWrapper<Item> implements ItemType {

    protected Identifier identifier;
    protected Item item;

    public NukkitItemType(Identifier identifier, Item handle) {
        super(handle);

        this.identifier = identifier;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public int getMaximumStackSize() {
        return item.getMaxStackSize();
    }

    public Item getHandle() {
        return item;
    }
}
