package org.battleplugins.sponge.world;

import org.battleplugins.entity.living.player.Player;
import org.battleplugins.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.sponge.util.SpongeUtil;
import org.battleplugins.sponge.world.block.SpongeBlock;
import org.battleplugins.sponge.world.block.entity.SpongeBlockEntity;
import org.battleplugins.sponge.world.block.entity.SpongeChest;
import org.battleplugins.sponge.world.block.entity.SpongeSign;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.Location;
import org.battleplugins.world.block.Block;
import org.battleplugins.world.block.entity.BlockEntity;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.world.World;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
        TileEntity blockEntity = handle.getTileEntity(SpongeUtil.toSpongeLocation(loc).getBlockPosition()).orElse(null);
        if (blockEntity instanceof Chest)
            return Optional.of((new SpongeChest((Chest) blockEntity)));

        if (blockEntity instanceof Sign)
            return Optional.of(new SpongeSign((Sign) blockEntity));

        return Optional.empty();
    }

    @Override
    public SpongeBlock getBlockAt(org.battleplugins.world.Location loc) {
        return new SpongeBlock(handle.getBlock(SpongeUtil.toSpongeLocation(loc).getBlockPosition())
                .snapshotFor(SpongeUtil.toSpongeLocation(loc)));
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

    @Override
    public void sendBlockUpdate(Player player, Location location, Block block) {
        ((SpongePlayer) player).getHandle().sendBlockChange(SpongeUtil.toSpongeLocation(location).getBlockPosition(),
                ((SpongeBlock) block).getHandle().getState());
    }

    @Override
    public void sendBlockEntityUpdate(Player player, Location location, BlockEntity blockEntity) {
        // oof
    }

    @Override
    public CompletableFuture<SpongeChunk> getChunkAt(int x, int z, boolean generate) {
        return handle.loadChunkAsync(x >> 4, 0, z >> 4, generate).thenApply(val -> val.map(SpongeChunk::new).get());
    }

    @Override
    public Optional<SpongeChunk> getChunkIfLoaded(int x, int z) {
        return handle.getChunkAtBlock(x >> 4, 0, z >> 4).map(SpongeChunk::new);
    }
}
