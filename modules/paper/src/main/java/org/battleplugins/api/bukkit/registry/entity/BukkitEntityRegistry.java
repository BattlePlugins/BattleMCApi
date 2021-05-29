package org.battleplugins.api.bukkit.registry.entity;

import org.battleplugins.api.bukkit.entity.BukkitEntityType;
import org.battleplugins.api.registry.entity.EntityRegistry;
import org.battleplugins.api.util.Identifier;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BukkitEntityRegistry extends EntityRegistry {

    @Override
    public Optional<org.battleplugins.api.entity.EntityType> fromIdentifier(Identifier identifier) {
        EntityType type = Registry.ENTITY_TYPE.get(NamespacedKey.fromString(identifier.toString()));
        return Optional.ofNullable(type).map(BukkitEntityType::new);
    }
}
