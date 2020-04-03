package org.battleplugins.api.sponge.registry.world;

import org.battleplugins.api.sponge.compat.SpongeCompatItemType;
import org.battleplugins.api.sponge.world.block.SpongeBlockType;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.registry.world.BlockRegistry;
import org.battleplugins.api.world.block.BlockType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;

import java.util.Optional;

public class SpongeBlockRegistry extends BlockRegistry {

    @Override
    public Optional<BlockType> fromIdentifier(Identifier identifier) {
        return SpongeCompatItemType.fromString(identifier.getKey())
                .map(itemType -> new SpongeBlockType(Sponge.getRegistry()
                        .getType(org.spongepowered.api.block.BlockType.class,
                                itemType.name()).orElse(BlockTypes.AIR)));
    }
}
