package org.battleplugins.api.bukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.api.bukkit.inventory.item.component.*;
import org.battleplugins.api.bukkit.world.block.BukkitBlockRegistry;
import org.battleplugins.api.bukkit.inventory.BukkitInventoryBuilder;

public class BukkitRegistry extends Registry {

    private BukkitItemRegistry itemRegistry;
    private BukkitBlockRegistry blockRegistry;

    BukkitRegistry() {
        itemRegistry = new BukkitItemRegistry();
        blockRegistry = new BukkitBlockRegistry();

        itemComponents.add(BukkitColorComponent.class);
        itemComponents.add(BukkitCustomModelDataComponent.class);
        itemComponents.add(BukkitDamageComponent.class);
        itemComponents.add(BukkitDisplayNameComponent.class);
        itemComponents.add(BukkitItemFlagComponent.class);
        itemComponents.add(BukkitLoreComponent.class);
        itemComponents.add(BukkitUnbreakableComponent.class);

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
}
