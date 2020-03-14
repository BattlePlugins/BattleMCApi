package org.battleplugins.api.sponge.registry.world;

import org.battleplugins.api.registry.world.DirectionRegistry;
import org.battleplugins.api.sponge.world.block.direction.SpongeDirection;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpongeDirectionRegistry extends DirectionRegistry {

    private static final Map<Identifier, Direction> IDENTIFIER_TO_BLOCK_FACE = new HashMap<>();

    static {
        for (org.spongepowered.api.util.Direction dir: org.spongepowered.api.util.Direction.values()) {
            SpongeDirection direction = new SpongeDirection(dir);
            IDENTIFIER_TO_BLOCK_FACE.put(direction.getIdentifier(), direction);
        }
    }

    @Override
    public Optional<Direction> fromIdentifier(Identifier identifier) {
        return Optional.ofNullable(IDENTIFIER_TO_BLOCK_FACE.get(identifier));
    }
}
