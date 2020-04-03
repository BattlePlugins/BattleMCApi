package org.battleplugins.api.sponge.registry.entity;

import org.battleplugins.api.registry.entity.EntityRegistry;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.sponge.entity.SpongeEntityType;
import org.battleplugins.api.util.Identifier;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeEntityRegistry extends EntityRegistry {

    @Override
    public Optional<EntityType> fromIdentifier(Identifier identifier) {
        return Sponge.getRegistry().getType(org.spongepowered.api.entity.EntityType.class, identifier.toString())
                .map(SpongeEntityType::new);
    }
}
