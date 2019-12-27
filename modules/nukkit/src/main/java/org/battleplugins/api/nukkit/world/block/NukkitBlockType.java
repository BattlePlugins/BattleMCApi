package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.block.BlockType;

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
