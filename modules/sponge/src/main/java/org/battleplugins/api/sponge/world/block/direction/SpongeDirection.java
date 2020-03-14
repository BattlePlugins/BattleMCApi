package org.battleplugins.api.sponge.world.block.direction;

import lombok.AllArgsConstructor;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.direction.Direction;

@AllArgsConstructor
public class SpongeDirection implements Direction {

    private org.spongepowered.api.util.Direction direction;

    @Override
    public Identifier getIdentifier() {
        return Identifier.minecraft(direction.name().toLowerCase());
    }

    @Override
    public Direction getOpposite() {
        return new SpongeDirection(direction.getOpposite());
    }
}
