package org.battleplugins.nukkit.inventory.item.component;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.CustomModelDataComponent;

import java.util.Optional;

public class NukkitCustomModelDataComponent implements CustomModelDataComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Integer modelData) {
        // unsupported
    }

    @Override
    public Optional<Integer> getValue(ItemStack itemStack) {
        return Optional.empty();
    }
}
