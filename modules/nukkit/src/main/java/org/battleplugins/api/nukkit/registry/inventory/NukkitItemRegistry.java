package org.battleplugins.api.nukkit.registry.inventory;

import cn.nukkit.item.Item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.battleplugins.api.nukkit.inventory.item.NukkitItemType;
import org.battleplugins.api.registry.inventory.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.nukkit.NukkitPlatform;
import org.battleplugins.api.nukkit.inventory.item.component.*;
import org.battleplugins.api.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NukkitItemRegistry extends ItemRegistry {

    private static final Map<ItemEntry, NukkitItemType> ITEM_ENTRIES = new HashMap<>();
    private static final Map<String, ItemEntry> IDENTIFIER_TO_ENTRY = new HashMap<>();

    public NukkitItemRegistry() {
        InputStream inputStream = NukkitPlatform.class.getResourceAsStream("/mappings/items.json");
        if (inputStream == null)
            throw new AssertionError("Items Table not found");

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, ItemEntry>>() {}.getType();
        Map<String, ItemEntry> itemEntries = gson.fromJson(new InputStreamReader(inputStream), mapType);
        for (Map.Entry<String, ItemEntry> itemEntry : itemEntries.entrySet()) {
            ITEM_ENTRIES.put(itemEntry.getValue(), new NukkitItemType(Identifier.of(itemEntry.getKey()),
                    Item.get(itemEntry.getValue().getBedrockId(), itemEntry.getValue().getBedrockData())));
            IDENTIFIER_TO_ENTRY.put(itemEntry.getKey(), itemEntry.getValue());
        }

        this.registerComponent(ColorComponent.class, new NukkitColorComponent());
        this.registerComponent(CustomModelDataComponent.class, new NukkitCustomModelDataComponent());
        this.registerComponent(DamageComponent.class, new NukkitDamageComponent());
        this.registerComponent(DisplayNameComponent.class, new NukkitDisplayNameComponent());
        this.registerComponent(ItemFlagComponent.class, new NukkitItemFlagComponent());
        this.registerComponent(LoreComponent.class, new NukkitLoreComponent());
        this.registerComponent(UnbreakableComponent.class, new NukkitUnbreakableComponent());
    }

    @Override
    public Optional<ItemType> fromIdentifier(Identifier identifier) {
        ItemEntry itemEntry = IDENTIFIER_TO_ENTRY.get(identifier.toString());
        if (itemEntry == null)
            return Optional.empty();

        return Optional.ofNullable(ITEM_ENTRIES.get(itemEntry));
    }
}
