package org.battleplugins.sponge.world;

import org.battleplugins.sponge.world.block.SpongeBlock;
import org.battleplugins.sponge.world.block.entity.SpongeBlockEntity;
import org.battleplugins.sponge.world.block.entity.SpongeChest;
import org.battleplugins.sponge.world.block.entity.SpongeSign;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.block.entity.BlockEntity;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class SpongeWorld extends MCWrapper<World> implements org.battleplugins.world.World {

    public SpongeWorld(World world) {
        super(world);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<SpongeBlockEntity> getBlockEntityAt(org.battleplugins.world.Location loc) {
        // instanceof returns false if null, so no need to check isPresent
        TileEntity blockEntity = handle.getTileEntity(((SpongeLocation) loc).getHandle().getBlockPosition()).orElse(null);
        if (blockEntity instanceof Chest)
            return Optional.of((new SpongeChest((Chest) blockEntity)));

        if (blockEntity instanceof Sign)
            return Optional.of(new SpongeSign((Sign) blockEntity));

        return Optional.empty();
    }

    @Override
    public SpongeBlock getBlockAt(org.battleplugins.world.Location loc) {
        return new SpongeBlock(handle.getBlock(((SpongeLocation) loc).getHandle().getBlockPosition()).snapshotFor(((SpongeLocation) loc).getHandle()));
    }

    @Override
    public boolean isType(BlockEntity blockEntity, Class<? extends BlockEntity> clazz) {
        try {
            BlockEntity b = toType(blockEntity, clazz);
            return b != null;
        } catch (ClassCastException e){
            return false;
        }
    }

    @Override
    public <T extends BlockEntity> T toType(BlockEntity blockEntity, Class<T> clazz) throws ClassCastException {
        if (clazz.isAssignableFrom(blockEntity.getClass()))
            return clazz.cast(blockEntity);

        TileEntity tileEntity = ((SpongeBlockEntity) blockEntity).getHandle();
        if (org.battleplugins.world.block.entity.Sign.class.isAssignableFrom(clazz)) {
            if (tileEntity instanceof Sign)
                return clazz.cast(new SpongeSign((Sign) tileEntity));

        } else if (org.battleplugins.world.block.entity.Chest.class.isAssignableFrom(clazz)) {
            if (tileEntity instanceof Chest)
                return clazz.cast(new SpongeChest((Chest) tileEntity));

        } else {
            throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
        }
        return null;
    }
}
