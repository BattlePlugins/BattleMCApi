package org.battleplugins.api.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntity;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.Block;
import org.battleplugins.api.nukkit.world.block.NukkitBlock;

public class NukkitBlockEntity<T extends BlockEntity> extends MCWrapper<T> implements org.battleplugins.api.world.block.entity.BlockEntity {

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
