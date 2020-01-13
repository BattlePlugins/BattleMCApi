package org.battleplugins.api.bukkit.entity;

import org.battleplugins.api.bukkit.entity.component.ageable.BukkitBabyComponent;
import org.battleplugins.api.entity.EntityRegistry;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.util.NamespacedKey;
import org.bukkit.entity.EntityType;

import java.util.Optional;

public class BukkitEntityRegistry extends EntityRegistry<EntityType> {

    public BukkitEntityRegistry() {
        registerComponent(BabyComponent.class, BukkitBabyComponent.class);
    }

    @Override
    public org.battleplugins.api.entity.EntityType fromPlatformType(EntityType type) {
        return new BukkitEntityType(type);
    }

    @Override
    public Optional<org.battleplugins.api.entity.EntityType> fromKey(NamespacedKey namespacedKey) {
        return Optional.ofNullable(EntityType.fromName(namespacedKey.getKey())).map(BukkitEntityType::new);
    }
}
