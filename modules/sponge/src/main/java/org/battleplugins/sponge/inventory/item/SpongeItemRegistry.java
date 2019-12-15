package org.battleplugins.sponge.inventory.item;

import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.sponge.compat.SpongeCompatItemType;
import org.battleplugins.util.NamespacedKey;

import java.util.Optional;

public class SpongeItemRegistry implements ItemRegistry<org.spongepowered.api.item.ItemType> {

    @Override
    public ItemType fromPlatformItem(org.spongepowered.api.item.ItemType item) {
        return null;
    }

    @Override
    public Optional<ItemType> fromKey(NamespacedKey namespacedKey) {
        // return BukkitMaterialAdapter.getMaterial(namespacedKey.getKey()).map(this::fromPlatformItem);
        return SpongeCompatItemType.fromString(namespacedKey.getKey())
                .map(itemType -> fromPlatformItem(itemType.parseItem().getType()));
    }
}
