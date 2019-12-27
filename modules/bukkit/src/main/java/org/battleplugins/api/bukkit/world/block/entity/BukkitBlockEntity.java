package org.battleplugins.api.bukkit.world.block.entity;

import org.battleplugins.api.bukkit.world.block.BukkitBlock;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.entity.BlockEntity;
import org.bukkit.block.BlockState;

public class BukkitBlockEntity<T extends BlockState> extends MCWrapper<T> implements BlockEntity {

    public BukkitBlockEntity(T handle) {
        super(handle);
    }

    @Override
    public BukkitBlock getBlock() {
        return new BukkitBlock(handle.getBlock());
    }

    @Override
    public void update() {
        handle.update();
    }

    @Override
    public void update(boolean update) {
        handle.update(true);
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
