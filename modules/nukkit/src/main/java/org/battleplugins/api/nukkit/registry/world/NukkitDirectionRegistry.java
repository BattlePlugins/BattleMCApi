package org.battleplugins.api.nukkit.registry.world;

import cn.nukkit.math.BlockFace;

import org.battleplugins.api.nukkit.world.direction.NukkitDirection;
import org.battleplugins.api.registry.world.DirectionRegistry;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NukkitDirectionRegistry extends DirectionRegistry {

    private static final Map<Identifier, Direction> IDENTIFIER_TO_BLOCK_FACE = new HashMap<>();

    static {
        for (BlockFace face : BlockFace.values()) {
            NukkitDirection direction = new NukkitDirection(face);
            IDENTIFIER_TO_BLOCK_FACE.put(direction.getIdentifier(), direction);
        }
    }

    @Override
    public Optional<Direction> fromIdentifier(Identifier identifier) {
        return Optional.ofNullable(IDENTIFIER_TO_BLOCK_FACE.get(identifier));
    }
}
