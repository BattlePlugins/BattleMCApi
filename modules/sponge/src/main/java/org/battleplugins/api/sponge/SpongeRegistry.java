package org.battleplugins.api.sponge;

import org.battleplugins.api.Registry;
import org.battleplugins.api.sponge.entity.SpongeEntityRegistry;
import org.battleplugins.api.sponge.inventory.SpongeInventoryBuilder;
import org.battleplugins.api.sponge.world.block.SpongeBlockRegistry;
import org.battleplugins.api.sponge.inventory.item.SpongeItemRegistry;

public class SpongeRegistry extends Registry {

    private SpongeItemRegistry itemRegistry;
    private SpongeBlockRegistry blockRegistry;
    private SpongeEntityRegistry entityRegistry;

    SpongeRegistry() {
        itemRegistry = new SpongeItemRegistry();
        blockRegistry = new SpongeBlockRegistry();
        entityRegistry = new SpongeEntityRegistry();

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

    @Override
    public SpongeEntityRegistry getEntityRegistry() {
        return entityRegistry;
    }
}
