package org.battleplugins.api.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DisplayNameComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

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

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
