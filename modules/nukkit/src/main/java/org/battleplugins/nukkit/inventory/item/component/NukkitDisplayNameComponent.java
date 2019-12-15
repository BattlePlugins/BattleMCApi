package org.battleplugins.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.DisplayNameComponent;
import org.battleplugins.nukkit.inventory.item.NukkitItemStack;

import java.util.Optional;

public class NukkitDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, String displayName) {
        ((NukkitItemStack) itemStack).getHandle().setCustomName(displayName);
    }

    @Override
    public Optional<String> getValue(ItemStack itemStack) {
        Item item = ((NukkitItemStack) itemStack).getHandle();
        if (!item.hasCustomName())
            return Optional.empty();

        return Optional.ofNullable(((NukkitItemStack) itemStack).getHandle().getCustomName());
    }
}
