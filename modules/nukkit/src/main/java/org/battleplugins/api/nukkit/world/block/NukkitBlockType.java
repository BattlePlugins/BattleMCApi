package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.nukkit.registry.inventory.NukkitItemRegistry;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.BlockType;

public class NukkitBlockType extends MCWrapper<Block> implements BlockType {

    public NukkitBlockType(Block handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((NukkitItemRegistry) Platform.getRegistry().getItemRegistry()).fromPlatformItem(handle.toItem());
    }

    @Override
    public float getHardness() {
        return (float) handle.getHardness();
    }
}
