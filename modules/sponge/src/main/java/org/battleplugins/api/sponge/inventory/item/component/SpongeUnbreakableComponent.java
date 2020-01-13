package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.UnbreakableComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.item.DurabilityData;

import java.util.Optional;

public class SpongeUnbreakableComponent implements UnbreakableComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Boolean unbreakable) {
        ((SpongeItemStack) itemStack).getHandle().offer(Keys.UNBREAKABLE, unbreakable);
    }

    @Override
    public Optional<Boolean> getValue(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(Keys.UNBREAKABLE);
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(DurabilityData.class).isPresent();
    }
}
