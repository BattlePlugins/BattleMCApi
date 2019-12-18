package org.battleplugins.sponge.inventory.item;

import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.sponge.compat.SpongeCompatItemType;
import org.battleplugins.util.NamespacedKey;
import org.spongepowered.api.Sponge;

import java.util.Optional;

public class SpongeItemRegistry implements ItemRegistry<org.spongepowered.api.item.ItemType> {

    @Override
    public ItemType fromPlatformItem(org.spongepowered.api.item.ItemType item) {
        return null;
    }

    @Override
    public Optional<ItemType> fromKey(NamespacedKey namespacedKey) {
        // return BukkitMaterialAdapter.getMaterial(namespacedKey.getKey()).map(this::fromPlatformItem);
        Optional<ItemType> compatItemType = SpongeCompatItemType.fromString(namespacedKey.getKey())
                .map(itemType -> fromPlatformItem(itemType.parseItem().getType()));
        if (!compatItemType.isPresent()) {
            // check for modded items
            return Sponge.getRegistry().getType(org.spongepowered.api.item.ItemType.class, namespacedKey.toString()).map(SpongeItemType::new);
        }
        return compatItemType;
    }
}
