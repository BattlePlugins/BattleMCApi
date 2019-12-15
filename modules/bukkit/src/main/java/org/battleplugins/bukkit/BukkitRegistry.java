package org.battleplugins.bukkit;

import org.battleplugins.Registry;
import org.battleplugins.bukkit.inventory.BukkitInventoryBuilder;
import org.battleplugins.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.bukkit.inventory.item.component.*;
import org.battleplugins.bukkit.world.block.BukkitBlockRegistry;

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
