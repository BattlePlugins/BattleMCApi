package org.battleplugins.sponge;

import org.battleplugins.Registry;
import org.battleplugins.sponge.inventory.SpongeInventoryBuilder;
import org.battleplugins.sponge.inventory.item.SpongeItemRegistry;
import org.battleplugins.sponge.inventory.item.component.*;
import org.battleplugins.sponge.world.block.SpongeBlockRegistry;

public class SpongeRegistry extends Registry {

    private SpongeItemRegistry itemRegistry;
    private SpongeBlockRegistry blockRegistry;

    SpongeRegistry() {
        itemRegistry = new SpongeItemRegistry();
        blockRegistry = new SpongeBlockRegistry();

        itemComponents.add(SpongeColorComponent.class);
        itemComponents.add(SpongeCustomModelDataComponent.class);
        itemComponents.add(SpongeDamageComponent.class);
        itemComponents.add(SpongeDisplayNameComponent.class);
        itemComponents.add(SpongeItemFlagComponent.class);
        itemComponents.add(SpongeLoreComponent.class);
        itemComponents.add(SpongeUnbreakableComponent.class);

        builders.add(SpongeInventoryBuilder.class);
    }

    @Override
    public SpongeItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    @Override
    public SpongeBlockRegistry getBlockRegistry() {
        return blockRegistry;
    }
}
