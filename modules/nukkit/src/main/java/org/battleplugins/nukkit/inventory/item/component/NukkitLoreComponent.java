package org.battleplugins.nukkit.inventory.item.component;

import cn.nukkit.item.Item;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.LoreComponent;
import org.battleplugins.nukkit.inventory.item.NukkitItemStack;

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
}
