package org.battleplugins.nukkit;

import org.battleplugins.Registry;
import org.battleplugins.nukkit.inventory.NukkitInventoryBuilder;
import org.battleplugins.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.nukkit.inventory.item.component.*;
import org.battleplugins.nukkit.world.block.NukkitBlockRegistry;

public class NukkitRegistry extends Registry {

    private NukkitItemRegistry itemRegistry;
    private NukkitBlockRegistry blockRegistry;

    NukkitRegistry() {
        itemRegistry = new NukkitItemRegistry();
        blockRegistry = new NukkitBlockRegistry();

        itemComponents.add(NukkitColorComponent.class);
        itemComponents.add(NukkitCustomModelDataComponent.class);
        itemComponents.add(NukkitDamageComponent.class);
        itemComponents.add(NukkitDisplayNameComponent.class);
        itemComponents.add(NukkitItemFlagComponent.class);
        itemComponents.add(NukkitLoreComponent.class);
        itemComponents.add(NukkitUnbreakableComponent.class);

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
}
