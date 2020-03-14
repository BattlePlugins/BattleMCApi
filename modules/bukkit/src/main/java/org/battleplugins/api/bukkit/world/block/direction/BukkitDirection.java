package org.battleplugins.api.bukkit.world.block.direction;

import lombok.AllArgsConstructor;

import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;
import org.bukkit.block.BlockFace;

@AllArgsConstructor
public class BukkitDirection implements Direction {

    private BlockFace blockFace;

    @Override
    public Identifier getIdentifier() {
        switch (blockFace) {
            case NORTH_EAST:
                return Identifier.minecraft("northeast");
            case NORTH_WEST:
                return Identifier.minecraft("northwest");
            case SOUTH_EAST:
                return Identifier.minecraft("southeast");
            case SOUTH_WEST:
                return Identifier.minecraft("southwest");
            case WEST_NORTH_WEST:
                return Identifier.minecraft("west_northwest");
            case NORTH_NORTH_WEST:
                return Identifier.minecraft("north_northwest");
            case NORTH_NORTH_EAST:
                return Identifier.minecraft("north_northeast");
            case EAST_NORTH_EAST:
                return Identifier.minecraft("east_northeast");
            case EAST_SOUTH_EAST:
                return Identifier.minecraft("east_southeast");
            case SOUTH_SOUTH_EAST:
                return Identifier.minecraft("south_southeast");
            case SOUTH_SOUTH_WEST:
                return Identifier.minecraft("south_southwest");
            case WEST_SOUTH_WEST:
                return Identifier.minecraft("west_southwest");
            case SELF:
                return Identifier.minecraft("none");
            default:
                return Identifier.minecraft(blockFace.name().toLowerCase());
        }
    }

    @Override
    public Direction getOpposite() {
        return new BukkitDirection(blockFace.getOppositeFace());
    }
}
