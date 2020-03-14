package org.battleplugins.api.nukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.nukkit.registry.entity.NukkitEntityRegistry;
import org.battleplugins.api.nukkit.entity.component.ageable.NukkitBabyComponent;
import org.battleplugins.api.nukkit.inventory.NukkitInventoryBuilder;
import org.battleplugins.api.nukkit.registry.inventory.NukkitItemRegistry;
import org.battleplugins.api.nukkit.registry.world.NukkitBlockRegistry;
import org.battleplugins.api.nukkit.registry.world.NukkitDirectionRegistry;
import org.battleplugins.api.registry.world.DirectionRegistry;

public class NukkitRegistry extends Registry {

    private NukkitItemRegistry itemRegistry;
    private NukkitBlockRegistry blockRegistry;
    private NukkitEntityRegistry entityRegistry;
    private NukkitDirectionRegistry directionRegistry;

    public void setup() {
        this.itemRegistry = new NukkitItemRegistry();
        this.blockRegistry = new NukkitBlockRegistry();
        this.entityRegistry = new NukkitEntityRegistry();
        this.directionRegistry = new NukkitDirectionRegistry();

        this.builders.add(NukkitInventoryBuilder.class);

        this.entityRegistry.registerComponent(BabyComponent.class, new NukkitBabyComponent());
    }

    @Override
    public NukkitItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    @Override
    public NukkitBlockRegistry getBlockRegistry() {
        return blockRegistry;
    }

    @Override
    public NukkitEntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    @Override
    public NukkitDirectionRegistry getDirectionRegistry() {
        return directionRegistry;
    }
}
