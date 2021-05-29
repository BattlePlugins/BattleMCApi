package org.battleplugins.api.bukkit.world;

import org.battleplugins.api.bukkit.entity.BukkitEntity;
import org.battleplugins.api.bukkit.world.block.entity.BukkitBlockEntity;
import org.battleplugins.api.util.MCWrapper;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class BukkitChunk extends MCWrapper<Chunk> implements org.battleplugins.api.world.Chunk {

    public BukkitChunk(Chunk handle) {
        super(handle);
    }

    @Override
    public BukkitWorld getWorld() {
        return new BukkitWorld(handle.getWorld());
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
        for (Entity entity : handle.getEntities()) {
            entities.add(new BukkitEntity<>(entity));
        }
        return entities;
    }

    @Override
    public List<BukkitBlockEntity> getBlockEntities() {
        List<BukkitBlockEntity> blockEntities = new ArrayList<>();
        for (BlockState blockEntity : handle.getTileEntities()) {
            blockEntities.add(new BukkitBlockEntity<>(blockEntity));
        }
        return blockEntities;
    }

    @Override
    public void loadChunk(boolean generate) {
        handle.load(generate);
    }

    @Override
    public void unloadChunk() {
        handle.unload();
    }
}
