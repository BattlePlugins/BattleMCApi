package org.battleplugins.api.bukkit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.util.Identifier;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BukkitEntityType implements EntityType {

    private org.bukkit.entity.EntityType type;

    @Override
    public Identifier getIdentifier() {
        return Identifier.minecraft(type.getName());
    }
}
