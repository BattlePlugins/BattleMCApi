package org.battleplugins.api.sponge.entity;

import org.battleplugins.api.entity.EntityRegistry;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.sponge.entity.component.ageable.SpongeBabyComponent;
import org.battleplugins.api.util.NamespacedKey;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeEntityRegistry extends EntityRegistry<org.spongepowered.api.entity.EntityType> {

    public SpongeEntityRegistry() {
        registerComponent(BabyComponent.class, SpongeBabyComponent.class);
    }

    @Override
    public EntityType fromPlatformType(org.spongepowered.api.entity.EntityType type) {
        return new SpongeEntityType(type);
    }

    @Override
    public Optional<EntityType> fromKey(NamespacedKey namespacedKey) {
        return Sponge.getRegistry().getType(org.spongepowered.api.entity.EntityType.class, namespacedKey.toString())
                .map(SpongeEntityType::new);
    }
}
