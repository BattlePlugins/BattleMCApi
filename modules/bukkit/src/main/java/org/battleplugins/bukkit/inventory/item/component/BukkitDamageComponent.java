package org.battleplugins.bukkit.inventory.item.component;

import org.battleplugins.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.DamageComponent;

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
}
