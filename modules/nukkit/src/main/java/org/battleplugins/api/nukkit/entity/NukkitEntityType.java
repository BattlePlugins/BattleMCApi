package org.battleplugins.api.nukkit.entity;

import lombok.AllArgsConstructor;

import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.nukkit.compat.NukkitCompatEntityType;
import org.battleplugins.api.util.Identifier;

import java.util.Arrays;

@AllArgsConstructor
public class NukkitEntityType implements EntityType {

    private int id;

    @Override
    public Identifier getIdentifier() {
        return Identifier.minecraft(
                Arrays.stream(NukkitCompatEntityType.values())
                        .filter(type -> type.getType() == this.id)
                        .map(NukkitCompatEntityType::name)
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)
        );
    }
}
