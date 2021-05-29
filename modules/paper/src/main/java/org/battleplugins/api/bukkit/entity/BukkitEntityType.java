package org.battleplugins.api.bukkit.entity;

import lombok.AllArgsConstructor;

import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.util.Identifier;

@AllArgsConstructor
public class BukkitEntityType implements EntityType {

    private org.bukkit.entity.EntityType type;

    @Override
    public Identifier getIdentifier() {
        return Identifier.minecraft(type.getName());
    }
}
