package org.battleplugins.api.nukkit.inventory.item;

import cn.nukkit.item.Item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.inventory.item.component.*;
import org.battleplugins.api.nukkit.NukkitPlatform;
import org.battleplugins.api.nukkit.inventory.item.component.*;
import org.battleplugins.api.util.NamespacedKey;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NukkitItemRegistry extends ItemRegistry<Item> {

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
            ITEM_ENTRIES.put(itemEntry.getValue(), new NukkitItemType(NamespacedKey.of(itemEntry.getKey()),
                    Item.get(itemEntry.getValue().getBedrockId(), itemEntry.getValue().getBedrockData())));
            IDENTIFIER_TO_ENTRY.put(itemEntry.getKey(), itemEntry.getValue());
        }

        registerComponent(ColorComponent.class, NukkitColorComponent.class);
        registerComponent(CustomModelDataComponent.class, NukkitCustomModelDataComponent.class);
        registerComponent(DamageComponent.class, NukkitDamageComponent.class);
        registerComponent(DisplayNameComponent.class, NukkitDisplayNameComponent.class);
        registerComponent(ItemFlagComponent.class, NukkitItemFlagComponent.class);
        registerComponent(LoreComponent.class, NukkitLoreComponent.class);
        registerComponent(UnbreakableComponent.class, NukkitUnbreakableComponent.class);
    }

    @Override
    public ItemType fromPlatformItem(Item item) {
        return ITEM_ENTRIES.get(new ItemEntry(item.getId(), item.getDamage()));
    }

    @Override
    public Optional<ItemType> fromKey(NamespacedKey namespacedKey) {
        ItemEntry itemEntry = IDENTIFIER_TO_ENTRY.get(namespacedKey.toString());
        if (itemEntry == null)
            return Optional.empty();

        return Optional.ofNullable(ITEM_ENTRIES.get(itemEntry));
    }
}
