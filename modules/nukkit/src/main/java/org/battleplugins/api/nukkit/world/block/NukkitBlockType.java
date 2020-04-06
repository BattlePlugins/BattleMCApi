package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemType;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.BlockType;

public class NukkitBlockType extends MCWrapper<Block> implements BlockType {

    public NukkitBlockType(Block handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return new NukkitItemType(Identifier.minecraft("stone"), handle.toItem()); // FIXME (waiting for 2.0)
    }

    @Override
    public float getHardness() {
        return (float) handle.getHardness();
    }
}
