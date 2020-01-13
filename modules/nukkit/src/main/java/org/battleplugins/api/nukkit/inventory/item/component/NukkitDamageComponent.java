package org.battleplugins.api.nukkit.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DamageComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.util.Optional;

public class NukkitDamageComponent implements DamageComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Short durability) {
        ((NukkitItemStack) itemStack).getHandle().setDamage((int) durability);
    }

    @Override
    public Optional<Short> getValue(ItemStack itemStack) {
        return Optional.of((short) ((NukkitItemStack) itemStack).getHandle().getDamage());
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
