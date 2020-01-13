package org.battleplugins.api.nukkit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.nukkit.compat.NukkitCompatEntityType;
import org.battleplugins.api.util.NamespacedKey;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class NukkitEntityType implements EntityType {

    private int id;

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(
                Arrays.stream(NukkitCompatEntityType.values())
                        .filter(type -> type.getType() == this.id)
                        .map(NukkitCompatEntityType::name)
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)
        );
    }
}
