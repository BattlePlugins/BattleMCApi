package org.battleplugins.sponge.world.block;

import org.battleplugins.sponge.util.SpongeUtil;
import org.battleplugins.sponge.world.SpongeWorld;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.Location;
import org.battleplugins.world.block.BlockType;
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
    public Location getLocation() {
        return SpongeUtil.fromSpongeLocation(handle.getLocation().get());
    }

    @Override
    public BlockType getType() {
        return new SpongeBlockType(handle.getState().getType());
    }

    @Override
    public SpongeBlock clone() {
        return new SpongeBlock(handle);
    }
}
