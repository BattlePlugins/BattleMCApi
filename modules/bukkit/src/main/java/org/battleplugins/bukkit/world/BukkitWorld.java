package org.battleplugins.bukkit.world;

import io.papermc.lib.PaperLib;

import org.battleplugins.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.bukkit.util.BukkitUtil;
import org.battleplugins.bukkit.world.block.BukkitBlock;
import org.battleplugins.bukkit.world.block.entity.BukkitBlockEntity;
import org.battleplugins.bukkit.world.block.entity.BukkitChest;
import org.battleplugins.bukkit.world.block.entity.BukkitSign;
import org.battleplugins.entity.living.player.Player;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.Location;
import org.battleplugins.world.block.Block;
import org.battleplugins.world.block.entity.BlockEntity;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BukkitWorld extends MCWrapper<World> implements org.battleplugins.world.World {

    public BukkitWorld(World world){
        super(world);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<BukkitBlockEntity> getBlockEntityAt(Location loc) {
        BlockState state = handle.getBlockAt(BukkitUtil.toBukkitLocation(loc)).getState();
        if (handle instanceof Chest)
            return Optional.of(new BukkitChest((Chest) state));

        if (handle instanceof Sign)
            return Optional.of(new BukkitSign((Sign) state));

        return Optional.empty();
    }

    @Override
    public BukkitBlock getBlockAt(Location loc) {
        return new BukkitBlock(handle.getBlockAt(BukkitUtil.toBukkitLocation(loc)));
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

        BlockState state = ((BukkitBlockEntity) blockEntity).getHandle();
        if (org.battleplugins.world.block.entity.Sign.class.isAssignableFrom(clazz)) {
            if (state instanceof Sign)
                return clazz.cast(new BukkitSign((Sign) state));

        } else if (org.battleplugins.world.block.entity.Chest.class.isAssignableFrom(clazz)) {
            if (state instanceof Chest)
                return clazz.cast(new BukkitChest((Chest) state));

        } else {
            throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
        }
        return null;
    }

    @Override
    public void sendBlockUpdate(Player player, Location location, Block block) {
        org.bukkit.entity.Player bukkitPlayer = ((BukkitPlayer) player).getHandle();
        org.bukkit.block.Block bukkitBlock = ((BukkitBlock) block).getHandle();
        bukkitPlayer.sendBlockChange(BukkitUtil.toBukkitLocation(location), bukkitBlock.getType(), bukkitBlock.getData());
    }

    @Override
    public void sendBlockEntityUpdate(Player player, Location location, BlockEntity blockEntity) {
        org.bukkit.entity.Player bukkitPlayer = ((BukkitPlayer) player).getHandle();
        if (blockEntity instanceof Sign) {
            bukkitPlayer.sendSignChange(BukkitUtil.toBukkitLocation(location), ((Sign) blockEntity).getLines());
        }
    }

    @Override
    public CompletableFuture<BukkitChunk> getChunkAt(int x, int z, boolean generate) {
        return PaperLib.getChunkAtAsync(handle, x, z, generate).thenApply(BukkitChunk::new);
    }

    @Override
    public Optional<BukkitChunk> getChunkIfLoaded(int x, int z) {
        return handle.isChunkLoaded(x, z) ? Optional.of(new BukkitChunk(handle.getChunkAt(x, z))) : Optional.empty();
    }
}
