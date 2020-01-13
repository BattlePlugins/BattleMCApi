package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.CustomModelDataComponent;

import java.util.Optional;

public class SpongeCustomModelDataComponent implements CustomModelDataComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Integer modelData) {
        // not implemented (sponge is still 1.12)
        // ((SpongeItemStack) itemStack).getHandle().offer(Keys.MODEL_DATA, modelData);
    }

    @Override
    public Optional<Integer> getValue(ItemStack itemStack) {
        return Optional.empty();
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        // not implemented (sponge is still 1.12)
        return false;
    }
}
