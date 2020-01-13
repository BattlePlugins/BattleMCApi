package org.battleplugins.api.bukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.bukkit.entity.BukkitEntityRegistry;
import org.battleplugins.api.bukkit.inventory.BukkitInventoryBuilder;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.api.bukkit.world.block.BukkitBlockRegistry;

public class BukkitRegistry extends Registry {

    private BukkitItemRegistry itemRegistry;
    private BukkitBlockRegistry blockRegistry;
    private BukkitEntityRegistry entityRegistry;

    BukkitRegistry() {
        itemRegistry = new BukkitItemRegistry();
        blockRegistry = new BukkitBlockRegistry();
        entityRegistry = new BukkitEntityRegistry();

        builders.add(BukkitInventoryBuilder.class);
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
