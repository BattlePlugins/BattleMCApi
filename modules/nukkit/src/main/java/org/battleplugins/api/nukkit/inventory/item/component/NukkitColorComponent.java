package org.battleplugins.api.nukkit.inventory.item.component;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemColorArmor;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.ColorComponent;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;

import java.awt.Color;
import java.util.Optional;

public class NukkitColorComponent implements ColorComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Color color) {
        Item item = ((NukkitItemStack) itemStack).getHandle();
        if (!(item instanceof ItemColorArmor))
            return;

        ItemColorArmor colorItem = (ItemColorArmor) item;
        colorItem.setColor(color.getRed(), color.getBlue(), color.getGreen());
    }

    @Override
    public Optional<Color> getValue(ItemStack itemStack) {
        cn.nukkit.item.Item item = ((NukkitItemStack) itemStack).getHandle();
        if (!(item instanceof ItemColorArmor))
            return Optional.empty();

        ItemColorArmor colorItem = (ItemColorArmor) item;
        return Optional.of(new Color(colorItem.getColor().getRGB()));
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return itemStack instanceof ItemColorArmor;
    }
}
