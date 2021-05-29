package org.battleplugins.api.bukkit.inventory.item.component;

import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.ColorComponent;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.awt.Color;
import java.util.Optional;

public class BukkitColorComponent implements ColorComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Color color) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        if (bukkitItemStack.getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) bukkitItemStack.getItemMeta();
            leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(color.getRGB()));
            bukkitItemStack.setItemMeta(leatherArmorMeta);
        }

        try {
            if (bukkitItemStack.getItemMeta() instanceof PotionMeta) {
                PotionMeta potionMeta = (PotionMeta) bukkitItemStack.getItemMeta();
                potionMeta.setColor(org.bukkit.Color.fromRGB(color.getRGB()));
                bukkitItemStack.setItemMeta(potionMeta);
            }
        } catch (Throwable ex) {
            // May not be supported in this version
        }
    }

    @Override
    public Optional<Color> getValue(ItemStack itemStack) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        if (bukkitItemStack.getItemMeta() instanceof LeatherArmorMeta) {
            org.bukkit.Color color = ((LeatherArmorMeta) bukkitItemStack.getItemMeta()).getColor();
            return Optional.of(new Color(color.getRed(), color.getBlue(), color.getGreen()));
        }

        try {
            if (bukkitItemStack.getItemMeta() instanceof PotionMeta) {
                org.bukkit.Color color = ((PotionMeta) bukkitItemStack.getItemMeta()).getColor();
                return Optional.of(new Color(color.getRed(), color.getBlue(), color.getGreen()));
            }
        } catch (Throwable ex) {
            // May not be supported in this version
        }

        return Optional.empty();
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        org.bukkit.inventory.ItemStack bukkitItemStack = ((BukkitItemStack) itemStack).getHandle();
        try {
            if (bukkitItemStack.getItemMeta() instanceof PotionMeta) {
               return true;
            }
        } catch (Throwable ex) {
            // May not be supported in this version
        }

        return bukkitItemStack.getItemMeta() instanceof LeatherArmorMeta;
    }
}
