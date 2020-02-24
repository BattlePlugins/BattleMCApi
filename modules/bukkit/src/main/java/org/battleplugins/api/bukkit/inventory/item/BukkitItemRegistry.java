package org.battleplugins.api.bukkit.inventory.item;

import org.battleplugins.api.bukkit.inventory.item.component.*;
import org.battleplugins.api.bukkit.util.BukkitMaterialAdapter;
import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.util.Identifier;
import org.bukkit.Material;

import java.util.Optional;

public class BukkitItemRegistry extends ItemRegistry<Material> {

    public BukkitItemRegistry() {
        registerComponent(ColorComponent.class, BukkitColorComponent.class);
        registerComponent(CustomModelDataComponent.class, BukkitCustomModelDataComponent.class);
        registerComponent(DamageComponent.class, BukkitDamageComponent.class);
        registerComponent(DisplayNameComponent.class, BukkitDisplayNameComponent.class);
        registerComponent(ItemFlagComponent.class, BukkitItemFlagComponent.class);
        registerComponent(LoreComponent.class, BukkitLoreComponent.class);
        registerComponent(UnbreakableComponent.class, BukkitUnbreakableComponent.class);
    }

    @Override
    public ItemType fromPlatformItem(Material material) {
        return new BukkitItemType(material);
    }

    @Override
    public Optional<ItemType> fromIdentifier(Identifier identifier) {
        return BukkitMaterialAdapter.getMaterial(identifier.getKey()).map(this::fromPlatformItem);
    }
}
