package org.battleplugins.api.sponge.world.block;

import org.battleplugins.api.sponge.compat.SpongeCompatItemType;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.block.BlockRegistry;
import org.battleplugins.api.world.block.BlockType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;

import java.util.Optional;

public class SpongeBlockRegistry implements BlockRegistry<org.spongepowered.api.block.BlockType> {

    @Override
    public BlockType fromPlatformBlock(org.spongepowered.api.block.BlockType block) {
        return new SpongeBlockType(block);
    }

    @Override
    public Optional<BlockType> fromKey(NamespacedKey namespacedKey) {
        return SpongeCompatItemType.fromString(namespacedKey.getKey())
                .map(itemType -> fromPlatformBlock(Sponge.getRegistry()
                        .getType(org.spongepowered.api.block.BlockType.class,
                                itemType.name()).orElse(BlockTypes.AIR)));
    }
}
