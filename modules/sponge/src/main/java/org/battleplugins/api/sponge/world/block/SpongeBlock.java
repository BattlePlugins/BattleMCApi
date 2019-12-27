package org.battleplugins.api.sponge.world.block;

import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.block.Block;
import org.battleplugins.api.sponge.util.SpongeUtil;
import org.battleplugins.api.sponge.world.SpongeWorld;
import org.battleplugins.api.world.block.BlockType;
import org.spongepowered.api.block.BlockSnapshot;

public class SpongeBlock extends MCWrapper<BlockSnapshot> implements Block {

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
