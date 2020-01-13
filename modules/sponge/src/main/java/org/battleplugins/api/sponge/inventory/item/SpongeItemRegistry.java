package org.battleplugins.api.sponge.inventory.item;

import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.sponge.compat.SpongeCompatItemType;
import org.battleplugins.api.util.NamespacedKey;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeItemRegistry implements ItemRegistry<org.spongepowered.api.item.ItemType> {

    @Override
    public ItemType fromPlatformItem(org.spongepowered.api.item.ItemType item) {
        return new SpongeItemType(item);
    }

    @Override
    public Optional<ItemType> fromKey(NamespacedKey namespacedKey) {
        Optional<ItemType> compatItemType = SpongeCompatItemType.fromString(namespacedKey.getKey())
                .map(itemType -> fromPlatformItem(itemType.parseItem().getType()));
        if (!compatItemType.isPresent()) {
            // check for modded items
            return Sponge.getRegistry().getType(org.spongepowered.api.item.ItemType.class, namespacedKey.toString()).map(SpongeItemType::new);
        }
        return compatItemType;
    }
}
