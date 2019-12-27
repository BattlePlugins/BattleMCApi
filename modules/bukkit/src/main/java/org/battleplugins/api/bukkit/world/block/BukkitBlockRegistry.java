package org.battleplugins.api.bukkit.world.block;

import org.battleplugins.api.world.block.BlockRegistry;
import org.battleplugins.api.bukkit.util.BukkitMaterialAdapter;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.block.BlockType;
import org.bukkit.Material;

import java.util.Optional;

public class BukkitBlockRegistry implements BlockRegistry<Material> {

    @Override
    public BlockType fromPlatformBlock(Material block) {
        return new BukkitBlockType(block);
    }

    @Override
    public Optional<BlockType> fromKey(NamespacedKey namespacedKey) {
        return BukkitMaterialAdapter.getMaterial(namespacedKey.getKey()).map(this::fromPlatformBlock);
    }
}
