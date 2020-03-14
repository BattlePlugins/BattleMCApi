package org.battleplugins.api.world.block.direction;

import org.battleplugins.api.registry.world.DirectionRegistry;
import org.battleplugins.api.util.Identifier;


import java.util.Optional;

/**
 * Holds all the valid {@link Direction}s.
 */
public class Directions {

    public static final Direction NORTH = getOrDefault("north");
    public static final Direction NORTH_NORTHEAST = getOrDefault("north_northeast");
    public static final Direction NORTH_NORTHWEST = getOrDefault("north_northwest");
    public static final Direction NORTHEAST = getOrDefault("northeast");
    public static final Direction NORTHWEST = getOrDefault("northwest");
    public static final Direction SOUTH = getOrDefault("south");
    public static final Direction SOUTH_SOUTHEAST = getOrDefault("south_southeast");
    public static final Direction SOUTH_SOUTHWEST = getOrDefault("south_southwest");
    public static final Direction SOUTHEAST = getOrDefault("southeast");
    public static final Direction SOUTHWEST = getOrDefault("southwest");
    public static final Direction EAST = getOrDefault("east");
    public static final Direction EAST_SOUTHEAST = getOrDefault("east_southeast");
    public static final Direction EAST_NORTHEAST = getOrDefault("east_northeast");
    public static final Direction WEST = getOrDefault("west");
    public static final Direction WEST_SOUTHWEST = getOrDefault("west_southwest");
    public static final Direction WEST_NORTHWEST = getOrDefault("west_northwest");
    public static final Direction UP = getOrDefault("up");
    public static final Direction DOWN = getOrDefault("down");
    public static final Direction NONE = getOrDefault("none");

    /**
     * Gets a {@link Direction} from the given {@link Identifier}
     *
     * @param key the identifier to get the item from
     * @return an block type from the given identifier
     */
    public static Optional<Direction> getDirectionFromIdentifier(Identifier key) {
        return DirectionRegistry.get().fromIdentifier(key);
    }

    private static Direction getOrDefault(String name) {
        return getDirectionFromIdentifier(Identifier.minecraft(name)).orElse(Directions.NORTH);
    }
}
