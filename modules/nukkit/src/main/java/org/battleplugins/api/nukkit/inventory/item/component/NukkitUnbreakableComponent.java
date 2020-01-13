package org.battleplugins.api.nukkit.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.UnbreakableComponent;

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

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return false;
    }
}
