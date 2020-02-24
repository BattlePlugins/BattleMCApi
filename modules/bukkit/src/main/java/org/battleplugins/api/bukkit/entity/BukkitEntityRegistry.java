package org.battleplugins.api.bukkit.entity;

import org.battleplugins.api.entity.EntityRegistry;
import org.battleplugins.api.util.Identifier;
import org.bukkit.entity.EntityType;

import java.util.Optional;

public class BukkitEntityRegistry extends EntityRegistry<EntityType> {

    @Override
    public org.battleplugins.api.entity.EntityType fromPlatformType(EntityType type) {
        return new BukkitEntityType(type);
    }

    @Override
    public Optional<org.battleplugins.api.entity.EntityType> fromIdentifier(Identifier identifier) {
        return Optional.ofNullable(EntityType.fromName(identifier.getKey())).map(BukkitEntityType::new);
    }
}
