package org.battleplugins.api.entity.component.ageable;

import static org.battleplugins.api.entity.EntityTypes.*;

import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.EntityTypes;
import org.battleplugins.api.entity.component.EntityComponent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the component responsible for getting/setting the
 * age of an entity.
 */
public interface BabyComponent extends EntityComponent<Boolean> {

    @Override
    default Set<EntityType> getValidEntityTypes() {

        return new HashSet<>(Arrays.asList(BEE, CAT, CHICKEN,
                COW, DONKEY, DROWNED, FOX, HORSE, HUSK, LLAMA,
                MULE, MOOSHROOM, OCELOT, PANDA, PIG, PANDA,
                POLAR_BEAR, RABBIT, SHEEP, SKELETON_HORSE,
                TURTLE, VILLAGER, WOLF, ZOMBIE, ZOMBIE_HORSE,
                ZOMBIE_PIGMAN, ZOMBIE_VILLAGER));
    }
}
