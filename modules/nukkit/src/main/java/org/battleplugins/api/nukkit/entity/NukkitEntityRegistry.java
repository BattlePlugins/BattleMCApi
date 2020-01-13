package org.battleplugins.api.nukkit.entity;

import org.battleplugins.api.entity.EntityRegistry;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.nukkit.compat.NukkitCompatEntityType;
import org.battleplugins.api.nukkit.entity.component.ageable.NukkitBabyComponent;
import org.battleplugins.api.util.NamespacedKey;

import java.util.Arrays;
import java.util.Optional;

public class NukkitEntityRegistry extends EntityRegistry<Integer> {

    public NukkitEntityRegistry() {
        registerComponent(BabyComponent.class, NukkitBabyComponent.class);
    }

    @Override
    public EntityType fromPlatformType(Integer type) {
        return new NukkitEntityType(type);
    }

    @Override
    public Optional<EntityType> fromKey(NamespacedKey namespacedKey) {
        return Arrays.stream(NukkitCompatEntityType.values())
                .filter(type -> type.name().equalsIgnoreCase(namespacedKey.getKey()))
                .map(type -> (EntityType) new NukkitEntityType(type.getType()))
                .findFirst();
    }
}
