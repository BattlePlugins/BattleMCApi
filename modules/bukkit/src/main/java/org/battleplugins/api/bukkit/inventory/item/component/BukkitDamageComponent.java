package org.battleplugins.api.bukkit.inventory.item.component;

import org.battleplugins.api.inventory.item.component.DamageComponent;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.Optional;

public class BukkitDamageComponent implements DamageComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Short durability) {
        ((BukkitItemStack) itemStack).getHandle().setDurability(durability);
    }

    @Override
    public Optional<Short> getValue(ItemStack itemStack) {
        return Optional.of(((BukkitItemStack) itemStack).getHandle().getDurability());
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        try {
            return ((BukkitItemStack) itemStack).getHandle() instanceof Damageable;
        } catch (Throwable ex) {
            // Pre 1.13, always return true
            return true;
        }
    }
}
