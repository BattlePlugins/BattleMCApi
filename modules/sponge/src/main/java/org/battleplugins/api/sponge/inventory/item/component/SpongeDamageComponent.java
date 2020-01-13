package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DamageComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.item.DurabilityData;

import java.util.Optional;

public class SpongeDamageComponent implements DamageComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Short durability) {
        ((SpongeItemStack) itemStack).getHandle().offer(Keys.ITEM_DURABILITY, (int) durability);
    }

    @Override
    public Optional<Short> getValue(ItemStack itemStack) {
       return ((SpongeItemStack) itemStack).getHandle().get(Keys.ITEM_DURABILITY).map(Integer::shortValue);
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(DurabilityData.class).isPresent();
    }
}
