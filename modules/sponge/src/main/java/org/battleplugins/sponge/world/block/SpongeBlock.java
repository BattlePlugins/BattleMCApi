package org.battleplugins.sponge.world.block;

import org.battleplugins.sponge.world.SpongeLocation;
import org.battleplugins.sponge.world.SpongeWorld;
import org.battleplugins.util.MCWrapper;
import org.spongepowered.api.block.BlockSnapshot;

public class SpongeBlock extends MCWrapper<BlockSnapshot> implements org.battleplugins.world.block.Block {

    public SpongeBlock(BlockSnapshot block) {
        super(block);
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getLocation().get().getExtent());
    }

    @Override
    public SpongeLocation getLocation() {
        return new SpongeLocation(handle.getLocation().get());
    }

    @Override
    public int getX() {
        return handle.getLocation().get().getBlockX();
    }

    @Override
    public int getY() {
        return handle.getLocation().get().getBlockY();
    }

    @Override
    public int getZ() {
        return handle.getLocation().get().getBlockZ();
    }

    @Override
    public String getType() {
        return handle.getState().getType().getName();
    }

    @Override
    public SpongeBlock clone() {
        return new SpongeBlock(handle);
    }
}
