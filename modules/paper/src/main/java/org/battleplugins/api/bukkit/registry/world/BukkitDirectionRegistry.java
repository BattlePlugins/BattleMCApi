package org.battleplugins.api.bukkit.registry.world;

import org.battleplugins.api.bukkit.world.block.direction.BukkitDirection;
import org.battleplugins.api.registry.world.DirectionRegistry;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;
import org.bukkit.block.BlockFace;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BukkitDirectionRegistry extends DirectionRegistry {

    private static final Map<Identifier, Direction> IDENTIFIER_TO_BLOCK_FACE = new HashMap<>();

    static {
        for (BlockFace face : BlockFace.values()) {
            BukkitDirection direction = new BukkitDirection(face);
            IDENTIFIER_TO_BLOCK_FACE.put(direction.getIdentifier(), direction);
        }
    }

    @Override
    public Optional<Direction> fromIdentifier(Identifier identifier) {
        return Optional.ofNullable(IDENTIFIER_TO_BLOCK_FACE.get(identifier));
    }
}
