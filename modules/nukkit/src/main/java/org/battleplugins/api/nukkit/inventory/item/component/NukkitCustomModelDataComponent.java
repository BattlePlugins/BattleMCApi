package org.battleplugins.api.nukkit.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.CustomModelDataComponent;

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
