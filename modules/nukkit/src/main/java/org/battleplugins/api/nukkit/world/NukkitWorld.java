package org.battleplugins.api.nukkit.world;

import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Level;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.UpdateBlockPacket;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;
import org.battleplugins.api.nukkit.util.NukkitUtil;
import org.battleplugins.api.nukkit.world.block.NukkitBlock;
import org.battleplugins.api.nukkit.world.block.entity.NukkitBlockEntity;
import org.battleplugins.api.nukkit.world.block.entity.NukkitChest;
import org.battleplugins.api.nukkit.world.block.entity.NukkitSign;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Chunk;
import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.World;
import org.battleplugins.api.world.block.Block;
import org.battleplugins.api.world.block.entity.BlockEntity;
import org.battleplugins.api.world.block.entity.Chest;
import org.battleplugins.api.world.block.entity.Sign;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class NukkitWorld extends MCWrapper<Level> implements World {

    public NukkitWorld(Level level){
        super(level);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<BlockEntity> getBlockEntityAt(Location loc) {
        cn.nukkit.blockentity.BlockEntity blockEntity = handle.getBlockEntity(NukkitUtil.toNukkitLocation(loc));
        if (blockEntity instanceof BlockEntityChest)
            return Optional.of(new NukkitChest((BlockEntityChest) blockEntity));

        if (blockEntity instanceof BlockEntitySign)
            return Optional.of(new NukkitSign((BlockEntitySign) blockEntity));

        return Optional.empty();
    }

    @Override
    public NukkitBlock getBlockAt(Location loc) {
        return new NukkitBlock(handle.getBlock(NukkitUtil.toNukkitLocation(loc)));
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

        cn.nukkit.blockentity.BlockEntity nukkitBlockEntity = ((NukkitBlockEntity) blockEntity).getHandle();
        if (Sign.class.isAssignableFrom(clazz)) {
            if (nukkitBlockEntity instanceof BlockEntitySign)
                return clazz.cast(new NukkitSign((BlockEntitySign) nukkitBlockEntity));

        } else if (Chest.class.isAssignableFrom(clazz)) {
            if (nukkitBlockEntity instanceof BlockEntityChest)
                return clazz.cast(new NukkitChest((BlockEntityChest) nukkitBlockEntity));

        } else {
            throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
        }
        return null;
    }

    @Override
    public void sendBlockUpdate(Player player, Location location, Block block) {
        cn.nukkit.block.Block nukkitBlock = ((NukkitBlock) block).getHandle();
        nukkitBlock.getLevel().sendBlocks(new cn.nukkit.Player[]{((NukkitPlayer) player).getHandle()},
                new cn.nukkit.block.Block[] {((NukkitBlock) block).getHandle()},
                UpdateBlockPacket.FLAG_ALL_PRIORITY);
    }

    @Override
    public void sendBlockEntityUpdate(Player player, Location location, BlockEntity blockEntity) {
        if (blockEntity instanceof Sign) {
            Sign sign = (Sign) blockEntity;
            CompoundTag nbt = new CompoundTag()
                    .putString("id", cn.nukkit.blockentity.BlockEntity.SIGN)
                    .putInt("x", location.getBlockX())
                    .putInt("y", location.getBlockY())
                    .putInt("z", location.getBlockZ())
                    .putString("Text1", "")
                    .putString("Text2", "")
                    .putString("Text3", "")
                    .putString("Text4", "");

            BlockEntitySign nukkitSign = new BlockEntitySign(NukkitUtil.toNukkitLocation(location).getChunk(), nbt);
            nukkitSign.setText(sign.getLines());
            nukkitSign.spawnTo(((NukkitPlayer) player).getHandle());
        }
    }

    @Override
    public CompletableFuture<Chunk> getChunkAt(int x, int z, boolean generate) {
        return CompletableFuture.completedFuture(new NukkitChunk(handle.getChunk(x, z, generate)));
    }

    @Override
    public Optional<Chunk> getChunkIfLoaded(int x, int z) {
        return Optional.ofNullable(handle.getChunkIfLoaded(x >> 4, z >> 4)).map(NukkitChunk::new);
    }
}
