package org.battleplugins.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.util.NamespacedKey;
import org.battleplugins.world.block.BlockType;

public class NukkitBlockType extends MCWrapper<Block> implements BlockType {

    protected NamespacedKey key;

    NukkitBlockType(NamespacedKey key, Block handle) {
        super(handle);

        this.key = key;
    }

    @Override
    public ItemType toItemType() {
        return ((NukkitItemRegistry) ItemRegistry.REGISTRY).fromPlatformItem(handle.toItem());
    }

    @Override
    public float getHardness() {
        return (float) handle.getHardness();
    }
}
