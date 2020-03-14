package org.battleplugins.api.nukkit.world.direction;

import cn.nukkit.math.BlockFace;

import lombok.AllArgsConstructor;

import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;

@AllArgsConstructor
public class NukkitDirection implements Direction {

    private BlockFace blockFace;

    @Override
    public Identifier getIdentifier() {
        return Identifier.minecraft(blockFace.name().toLowerCase());
    }

    @Override
    public Direction getOpposite() {
        return new NukkitDirection(blockFace.getOpposite());
    }
}
