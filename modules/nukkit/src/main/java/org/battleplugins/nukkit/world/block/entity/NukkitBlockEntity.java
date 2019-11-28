package org.battleplugins.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntity;
import org.battleplugins.nukkit.world.block.NukkitBlock;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.block.Block;

public class NukkitBlockEntity<T extends BlockEntity> extends MCWrapper<T> implements org.battleplugins.world.block.entity.BlockEntity {

    public NukkitBlockEntity(T handle) {
        super(handle);
    }

    @Override
    public Block getBlock() {
        return new NukkitBlock(handle.getBlock());
    }

    @Override
    public void update() {
        handle.scheduleUpdate();
    }

    @Override
    public void update(boolean update) {
        handle.scheduleUpdate();
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
