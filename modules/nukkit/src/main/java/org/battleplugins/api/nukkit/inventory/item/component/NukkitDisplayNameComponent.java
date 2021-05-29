package org.battleplugins.api.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DisplayNameComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.util.Optional;

public class NukkitDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Component displayName) {
        ((NukkitItemStack) itemStack).getHandle().setCustomName(LegacyComponentSerializer.legacySection().serialize(displayName));
    }

    @Override
    public Optional<Component> getValue(ItemStack itemStack) {
        Item item = ((NukkitItemStack) itemStack).getHandle();
        if (!item.hasCustomName())
            return Optional.empty();

        return Optional.of(LegacyComponentSerializer.legacySection().deserialize(((NukkitItemStack) itemStack).getHandle().getCustomName()));
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
