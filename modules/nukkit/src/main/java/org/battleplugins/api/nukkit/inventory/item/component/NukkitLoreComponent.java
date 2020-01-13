package org.battleplugins.api.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.LoreComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NukkitLoreComponent implements LoreComponent {

    @Override
    public void applyComponent(ItemStack itemStack, List<String> lore) {
        Item item = ((NukkitItemStack) itemStack).getHandle();
        item.setLore(lore.toArray(new String[0]));
    }

    @Override
    public Optional<List<String>> getValue(ItemStack itemStack) {
        return Optional.of(Arrays.asList(((NukkitItemStack) itemStack).getHandle().getLore()));
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
