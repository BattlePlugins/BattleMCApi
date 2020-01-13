package org.battleplugins.api.nukkit.entity.component.ageable;

import static cn.nukkit.entity.Entity.DATA_FLAGS;
import static cn.nukkit.entity.Entity.DATA_FLAG_BABY;

import cn.nukkit.entity.EntityAgeable;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.EntityTypes;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.nukkit.entity.NukkitEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class NukkitBabyComponent implements BabyComponent {

    @Override
    public void applyComponent(Entity entity, Boolean baby) {
        cn.nukkit.entity.Entity nukkitEntity = ((NukkitEntity) entity).getHandle();
        if (entity instanceof EntityAgeable) {
            nukkitEntity.setDataFlag(DATA_FLAGS, DATA_FLAG_BABY, baby);
        }
    }

    @Override
    public Optional<Boolean> getValue(Entity entity) {
        cn.nukkit.entity.Entity nukkitEntity = ((NukkitEntity) entity).getHandle();
        if (entity instanceof EntityAgeable) {
            return Optional.of(nukkitEntity.getDataFlag(DATA_FLAGS, DATA_FLAG_BABY));
        }
        return Optional.empty();
    }

    @Override
    public boolean isAppliable(Entity entity) {
        return ((NukkitEntity) entity).getHandle() instanceof EntityAgeable;
    }

    @Override
    public Set<EntityType> getValidEntityTypes() {
        Set<EntityType> validTypes = BabyComponent.super.getValidEntityTypes();
        validTypes.addAll(Arrays.asList(EntityTypes.DOLPHIN, EntityTypes.SQUID));
        return validTypes;
    }
}
