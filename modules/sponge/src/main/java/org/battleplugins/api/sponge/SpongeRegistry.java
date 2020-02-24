package org.battleplugins.api.sponge;

import org.battleplugins.api.Registry;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.sponge.entity.SpongeEntityRegistry;
import org.battleplugins.api.sponge.entity.component.ageable.SpongeBabyComponent;
import org.battleplugins.api.sponge.inventory.SpongeInventoryBuilder;
import org.battleplugins.api.sponge.world.block.SpongeBlockRegistry;
import org.battleplugins.api.sponge.inventory.item.SpongeItemRegistry;

public class SpongeRegistry extends Registry {

    private SpongeItemRegistry itemRegistry;
    private SpongeBlockRegistry blockRegistry;
    private SpongeEntityRegistry entityRegistry;

    public void setup() {
        this.itemRegistry = new SpongeItemRegistry();
        this.blockRegistry = new SpongeBlockRegistry();
        this.entityRegistry = new SpongeEntityRegistry();

        this.builders.add(SpongeInventoryBuilder.class);

        this.entityRegistry.registerComponent(BabyComponent.class, new SpongeBabyComponent());
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
