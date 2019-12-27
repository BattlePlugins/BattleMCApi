package org.battleplugins.api.nukkit;

import org.battleplugins.api.Registry;
import org.battleplugins.api.nukkit.inventory.item.component.*;
import org.battleplugins.api.nukkit.world.block.NukkitBlockRegistry;
import org.battleplugins.api.nukkit.inventory.NukkitInventoryBuilder;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemRegistry;
import org.battleplugins.api.nukkit.inventory.item.component.*;

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
