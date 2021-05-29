package org.battleplugins.api.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.LoreComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NukkitLoreComponent implements LoreComponent {

    @Override
    public void applyComponent(ItemStack itemStack, List<Component> lore) {
        Item item = ((NukkitItemStack) itemStack).getHandle();
        item.setLore(lore.stream().map(LegacyComponentSerializer.legacySection()::serialize).toArray(String[]::new));
    }

    @Override
    public Optional<List<Component>> getValue(ItemStack itemStack) {
        return Optional.of(Arrays.stream(((NukkitItemStack) itemStack).getHandle().getLore()).
                map(LegacyComponentSerializer.legacySection()::deserialize)
                .collect(Collectors.toList())
        );
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
