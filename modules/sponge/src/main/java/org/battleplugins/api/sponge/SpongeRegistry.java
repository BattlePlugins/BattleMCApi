package org.battleplugins.api.sponge;

import org.battleplugins.api.Registry;
import org.battleplugins.api.entity.EntityRegistry;
import org.battleplugins.api.sponge.entity.SpongeEntityRegistry;
import org.battleplugins.api.sponge.entity.component.ageable.SpongeBabyComponent;
import org.battleplugins.api.sponge.inventory.SpongeInventoryBuilder;
import org.battleplugins.api.sponge.inventory.item.component.*;
import org.battleplugins.api.sponge.world.block.SpongeBlockRegistry;
import org.battleplugins.api.sponge.inventory.item.SpongeItemRegistry;
import org.battleplugins.api.sponge.inventory.item.component.*;

public class SpongeRegistry extends Registry {

    private SpongeItemRegistry itemRegistry;
    private SpongeBlockRegistry blockRegistry;
    private SpongeEntityRegistry entityRegistry;

    SpongeRegistry() {
        itemRegistry = new SpongeItemRegistry();
        blockRegistry = new SpongeBlockRegistry();
        entityRegistry = new SpongeEntityRegistry();

        itemComponents.add(SpongeColorComponent.class);
        itemComponents.add(SpongeCustomModelDataComponent.class);
        itemComponents.add(SpongeDamageComponent.class);
        itemComponents.add(SpongeDisplayNameComponent.class);
        itemComponents.add(SpongeItemFlagComponent.class);
        itemComponents.add(SpongeLoreComponent.class);
        itemComponents.add(SpongeUnbreakableComponent.class);

        entityComponents.add(SpongeBabyComponent.class);

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
