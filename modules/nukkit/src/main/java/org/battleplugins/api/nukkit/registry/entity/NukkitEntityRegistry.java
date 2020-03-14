package org.battleplugins.api.nukkit.registry.entity;

import org.battleplugins.api.nukkit.entity.NukkitEntityType;
import org.battleplugins.api.registry.entity.EntityRegistry;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.nukkit.compat.NukkitCompatEntityType;
import org.battleplugins.api.util.Identifier;

import java.util.Arrays;
import java.util.Optional;

public class NukkitEntityRegistry extends EntityRegistry<Integer> {

    @Override
    public EntityType fromPlatformType(Integer type) {
        return new NukkitEntityType(type);
    }

    @Override
    public Optional<EntityType> fromIdentifier(Identifier identifier) {
        return Arrays.stream(NukkitCompatEntityType.values())
                .filter(type -> type.name().equalsIgnoreCase(identifier.getKey()))
                .map(type -> (EntityType) new NukkitEntityType(type.getType()))
                .findFirst();
    }
}
