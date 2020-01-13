package org.battleplugins.api.nukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.nukkit.entity.NukkitEntityRegistry;
import org.battleplugins.api.nukkit.inventory.NukkitInventoryBuilder;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.api.nukkit.world.block.NukkitBlockRegistry;

public class NukkitRegistry extends Registry {

    private NukkitItemRegistry itemRegistry;
    private NukkitBlockRegistry blockRegistry;
    private NukkitEntityRegistry entityRegistry;

    NukkitRegistry() {
        itemRegistry = new NukkitItemRegistry();
        blockRegistry = new NukkitBlockRegistry();
        entityRegistry = new NukkitEntityRegistry();

        builders.add(NukkitInventoryBuilder.class);
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
}
