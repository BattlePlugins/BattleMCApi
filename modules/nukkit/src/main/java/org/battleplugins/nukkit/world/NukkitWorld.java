package org.battleplugins.nukkit.world;

import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

import org.battleplugins.nukkit.world.block.NukkitBlock;
import org.battleplugins.nukkit.world.block.entity.NukkitBlockEntity;
import org.battleplugins.nukkit.world.block.entity.NukkitChest;
import org.battleplugins.nukkit.world.block.entity.NukkitSign;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.block.entity.BlockEntity;

import java.util.Optional;

public class NukkitWorld extends MCWrapper<Level> implements org.battleplugins.world.World {

    public NukkitWorld(Level level){
        super(level);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Optional<NukkitBlockEntity> getBlockEntityAt(org.battleplugins.world.Location loc) {
        cn.nukkit.blockentity.BlockEntity blockEntity = handle.getBlockEntity(((NukkitLocation) loc).getHandle());
        if (blockEntity instanceof BlockEntityChest)
            return Optional.of(new NukkitChest((BlockEntityChest) blockEntity));

        if (blockEntity instanceof BlockEntitySign)
            return Optional.of(new NukkitSign((BlockEntitySign) blockEntity));

        return Optional.empty();
    }

    @Override
    public NukkitBlock getBlockAt(org.battleplugins.world.Location loc) {
        return new NukkitBlock(handle.getBlock(((NukkitLocation) loc).getHandle()));
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
        if (org.battleplugins.world.block.entity.Sign.class.isAssignableFrom(clazz)) {
            if (nukkitBlockEntity instanceof BlockEntitySign)
                return clazz.cast(new NukkitSign((BlockEntitySign) nukkitBlockEntity));

        } else if (org.battleplugins.world.block.entity.Chest.class.isAssignableFrom(clazz)) {
            if (nukkitBlockEntity instanceof BlockEntityChest)
                return clazz.cast(new NukkitChest((BlockEntityChest) nukkitBlockEntity));

        } else {
            throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
        }
        return null;
    }
}
