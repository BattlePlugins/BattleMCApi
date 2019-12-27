package org.battleplugins.api.sponge.world;

import org.battleplugins.api.sponge.entity.SpongeEntity;
import org.battleplugins.api.sponge.world.block.entity.SpongeBlockEntity;
import org.battleplugins.api.util.MCWrapper;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.world.Chunk;

import java.util.ArrayList;
import java.util.List;

public class SpongeChunk extends MCWrapper<Chunk> implements org.battleplugins.api.world.Chunk {

    public SpongeChunk(Chunk handle) {
        super(handle);
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getWorld());
    }

    @Override
    public int getX() {
        return handle.getPosition().getX();
    }

    @Override
    public int getZ() {
        return handle.getPosition().getZ();
    }

    @Override
    public boolean isLoaded() {
        return handle.isLoaded();
    }

    @Override
    public List<SpongeEntity> getEntities() {
        List<SpongeEntity> entities = new ArrayList<>();
        for (Entity entity : handle.getEntities()) {
            entities.add(new SpongeEntity<>(entity));
        }
        return entities;
    }

    @Override
    public List<SpongeBlockEntity> getBlockEntities() {
        List<SpongeBlockEntity> blockEntities = new ArrayList<>();
        for (TileEntity blockEntity : handle.getTileEntities()) {
             blockEntities.add(new SpongeBlockEntity<>(blockEntity));
        }
        return blockEntities;
    }

    @Override
    public void loadChunk(boolean generate) {
        handle.loadChunk(generate);
    }

    @Override
    public void unloadChunk() {
        handle.unloadChunk();
    }
}
