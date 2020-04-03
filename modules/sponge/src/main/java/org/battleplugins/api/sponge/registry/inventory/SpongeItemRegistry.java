package org.battleplugins.api.sponge.registry.inventory;

import org.battleplugins.api.registry.inventory.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.sponge.compat.SpongeCompatItemType;
import org.battleplugins.api.sponge.inventory.item.SpongeItemType;
import org.battleplugins.api.sponge.inventory.item.component.*;
import org.battleplugins.api.util.Identifier;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeItemRegistry extends ItemRegistry {

    public SpongeItemRegistry() {
        this.registerComponent(ColorComponent.class, new SpongeColorComponent());
        this.registerComponent(CustomModelDataComponent.class, new SpongeCustomModelDataComponent());
        this.registerComponent(DamageComponent.class, new SpongeDamageComponent());
        this.registerComponent(DisplayNameComponent.class, new SpongeDisplayNameComponent());
        this.registerComponent(ItemFlagComponent.class, new SpongeItemFlagComponent());
        this.registerComponent(LoreComponent.class, new SpongeLoreComponent());
        this.registerComponent(UnbreakableComponent.class, new SpongeUnbreakableComponent());
    }

    @Override
    public Optional<ItemType> fromIdentifier(Identifier identifier) {
        Optional<ItemType> compatItemType = SpongeCompatItemType.fromString(identifier.getKey())
                .map(itemType -> new SpongeItemType(itemType.parseItem().getType()));
        if (!compatItemType.isPresent()) {
            // check for modded items
            return Sponge.getRegistry().getType(org.spongepowered.api.item.ItemType.class, identifier.toString()).map(SpongeItemType::new);
        }
        return compatItemType;
    }
}
