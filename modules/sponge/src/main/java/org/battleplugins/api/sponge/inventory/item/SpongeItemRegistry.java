package org.battleplugins.api.sponge.inventory.item;

import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.sponge.compat.SpongeCompatItemType;
import org.battleplugins.api.sponge.inventory.item.component.*;
import org.battleplugins.api.util.Identifier;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeItemRegistry extends ItemRegistry<org.spongepowered.api.item.ItemType> {

    public SpongeItemRegistry() {
        registerComponent(ColorComponent.class, SpongeColorComponent.class);
        registerComponent(CustomModelDataComponent.class, SpongeCustomModelDataComponent.class);
        registerComponent(DamageComponent.class, SpongeDamageComponent.class);
        registerComponent(DisplayNameComponent.class, SpongeDisplayNameComponent.class);
        registerComponent(ItemFlagComponent.class, SpongeItemFlagComponent.class);
        registerComponent(LoreComponent.class, SpongeLoreComponent.class);
        registerComponent(UnbreakableComponent.class, SpongeUnbreakableComponent.class);
    }

    @Override
    public ItemType fromPlatformItem(org.spongepowered.api.item.ItemType item) {
        return new SpongeItemType(item);
    }

    @Override
    public Optional<ItemType> fromIdentifier(Identifier identifier) {
        Optional<ItemType> compatItemType = SpongeCompatItemType.fromString(identifier.getKey())
                .map(itemType -> fromPlatformItem(itemType.parseItem().getType()));
        if (!compatItemType.isPresent()) {
            // check for modded items
            return Sponge.getRegistry().getType(org.spongepowered.api.item.ItemType.class, identifier.toString()).map(SpongeItemType::new);
        }
        return compatItemType;
    }
}
