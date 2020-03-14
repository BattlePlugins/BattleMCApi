package org.battleplugins.api.bukkit.registry.world;

import org.battleplugins.api.bukkit.world.block.BukkitBlockType;
import org.battleplugins.api.registry.world.BlockRegistry;
import org.battleplugins.api.bukkit.util.BukkitMaterialAdapter;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.BlockType;
import org.bukkit.Material;

import java.util.Optional;

public class BukkitBlockRegistry extends BlockRegistry<Material> {

    @Override
    public BlockType fromPlatformBlock(Material block) {
        return new BukkitBlockType(block);
    }

    @Override
    public Optional<BlockType> fromIdentifier(Identifier identifier) {
        return BukkitMaterialAdapter.getMaterial(identifier.getKey()).map(this::fromPlatformBlock);
    }
}
