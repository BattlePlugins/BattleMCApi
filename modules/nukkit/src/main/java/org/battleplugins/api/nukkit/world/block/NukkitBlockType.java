package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.BlockType;

public class NukkitBlockType extends MCWrapper<Block> implements BlockType {

    NukkitBlockType(Block handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((NukkitItemRegistry) Platform.getRegistry().getBlockRegistry()).fromPlatformItem(handle.toItem());
    }

    @Override
    public float getHardness() {
        return (float) handle.getHardness();
    }
}
