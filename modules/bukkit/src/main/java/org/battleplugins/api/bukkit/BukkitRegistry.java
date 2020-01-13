package org.battleplugins.api.bukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.bukkit.entity.BukkitEntityRegistry;
import org.battleplugins.api.bukkit.entity.component.ageable.BukkitBabyComponent;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.api.bukkit.inventory.item.component.*;
import org.battleplugins.api.bukkit.world.block.BukkitBlockRegistry;
import org.battleplugins.api.bukkit.inventory.BukkitInventoryBuilder;

public class BukkitRegistry extends Registry {

    private BukkitItemRegistry itemRegistry;
    private BukkitBlockRegistry blockRegistry;
    private BukkitEntityRegistry entityRegistry;

    BukkitRegistry() {
        itemRegistry = new BukkitItemRegistry();
        blockRegistry = new BukkitBlockRegistry();
        entityRegistry = new BukkitEntityRegistry();

        itemComponents.add(BukkitColorComponent.class);
        itemComponents.add(BukkitCustomModelDataComponent.class);
        itemComponents.add(BukkitDamageComponent.class);
        itemComponents.add(BukkitDisplayNameComponent.class);
        itemComponents.add(BukkitItemFlagComponent.class);
        itemComponents.add(BukkitLoreComponent.class);
        itemComponents.add(BukkitUnbreakableComponent.class);

        entityComponents.add(BukkitBabyComponent.class);

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
