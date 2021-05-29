package org.battleplugins.api.bukkit.registry.inventory;

import org.battleplugins.api.bukkit.inventory.item.BukkitItemType;
import org.battleplugins.api.bukkit.inventory.item.component.*;
import org.battleplugins.api.bukkit.util.BukkitMaterialAdapter;
import org.battleplugins.api.registry.inventory.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.util.Identifier;

import java.util.Optional;

public class BukkitItemRegistry extends ItemRegistry {

    public BukkitItemRegistry() {
        this.registerComponent(ColorComponent.class, new BukkitColorComponent());
        this.registerComponent(CustomModelDataComponent.class, new BukkitCustomModelDataComponent());
        this.registerComponent(DamageComponent.class, new BukkitDamageComponent());
        this.registerComponent(DisplayNameComponent.class, new BukkitDisplayNameComponent());
        this.registerComponent(ItemFlagComponent.class, new BukkitItemFlagComponent());
        this.registerComponent(LoreComponent.class, new BukkitLoreComponent());
        this.registerComponent(UnbreakableComponent.class, new BukkitUnbreakableComponent());
    }

    @Override
    public Optional<ItemType> fromIdentifier(Identifier identifier) {
        return BukkitMaterialAdapter.getMaterial(identifier.getKey()).map(BukkitItemType::new);
    }
}
