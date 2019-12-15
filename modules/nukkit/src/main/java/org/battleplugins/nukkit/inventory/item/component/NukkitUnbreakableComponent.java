package org.battleplugins.nukkit.inventory.item.component;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.UnbreakableComponent;

import java.util.Optional;

public class NukkitUnbreakableComponent implements UnbreakableComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Boolean unbreakable) {
        // unsupported
    }

    @Override
    public Optional<Boolean> getValue(ItemStack itemStack) {
        return Optional.empty();
    }
}
