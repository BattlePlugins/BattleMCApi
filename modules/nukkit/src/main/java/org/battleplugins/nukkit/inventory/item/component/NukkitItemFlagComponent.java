package org.battleplugins.nukkit.inventory.item.component;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.ItemFlagComponent;
import org.battleplugins.inventory.item.component.flag.ItemFlag;

import java.util.Optional;
import java.util.Set;

public class NukkitItemFlagComponent implements ItemFlagComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Set<ItemFlag> itemFlags) {
        // unsupported
    }

    @Override
    public Optional<Set<ItemFlag>> getValue(ItemStack itemStack) {
        return Optional.empty();
    }
}
