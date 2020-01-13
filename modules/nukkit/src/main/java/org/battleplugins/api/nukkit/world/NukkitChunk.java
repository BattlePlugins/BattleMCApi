package org.battleplugins.api.nukkit.world;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.format.FullChunk;

import org.battleplugins.api.nukkit.entity.NukkitEntity;
import org.battleplugins.api.nukkit.world.block.entity.NukkitBlockEntity;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Chunk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NukkitChunk extends MCWrapper<FullChunk> implements Chunk {

    public NukkitChunk(FullChunk handle) {
        super(handle);
    }

    @Override
    public NukkitWorld getWorld() {
        return new NukkitWorld(handle.getProvider().getLevel());
    }

    @Override
    public int getX() {
        return handle.getX();
    }

    @Override
    public int getZ() {
        return handle.getZ();
    }

    @Override
    public boolean isLoaded() {
        return handle.isLoaded();
    }

    @Override
    public List<org.battleplugins.api.entity.Entity> getEntities() {
        List<org.battleplugins.api.entity.Entity> entities = new ArrayList<>();
        for (Entity entity : handle.getEntities().values()) {
            entities.add(new NukkitEntity<>(entity));
        }
        return entities;
    }

    @Override
    public List<NukkitBlockEntity> getBlockEntities() {
        List<NukkitBlockEntity> blockEntities = new ArrayList<>();
        for (BlockEntity blockEntity : handle.getBlockEntities().values()) {
            blockEntities.add(new NukkitBlockEntity<>(blockEntity));
        }
        return blockEntities;
    }

    @Override
    public void loadChunk(boolean generate) {
        try {
            handle.load(generate);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void unloadChunk() {
        try {
            handle.unload();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
