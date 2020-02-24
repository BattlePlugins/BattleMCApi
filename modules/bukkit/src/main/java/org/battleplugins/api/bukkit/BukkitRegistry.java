package org.battleplugins.api.bukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.bukkit.entity.BukkitEntityRegistry;
import org.battleplugins.api.bukkit.entity.component.ageable.BukkitBabyComponent;
import org.battleplugins.api.bukkit.inventory.BukkitInventoryBuilder;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.api.bukkit.world.block.BukkitBlockRegistry;
import org.battleplugins.api.entity.component.ageable.BabyComponent;

public class BukkitRegistry extends Registry {

    private BukkitItemRegistry itemRegistry;
    private BukkitBlockRegistry blockRegistry;
    private BukkitEntityRegistry entityRegistry;

    public void setup() {
        this.itemRegistry = new BukkitItemRegistry();
        this.blockRegistry = new BukkitBlockRegistry();
        this.entityRegistry = new BukkitEntityRegistry();

        this.builders.add(BukkitInventoryBuilder.class);

        this.entityRegistry.registerComponent(BabyComponent.class, new BukkitBabyComponent());
    }

    @Override
    public BukkitItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    @Override
    public BukkitBlockRegistry getBlockRegistry() {
        return blockRegistry;
    }

    @Override
    public BukkitEntityRegistry getEntityRegistry() {
        return entityRegistry;
    }
}
