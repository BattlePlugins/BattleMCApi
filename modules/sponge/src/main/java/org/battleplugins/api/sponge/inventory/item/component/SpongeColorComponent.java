package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.ColorComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;

import java.awt.Color;
import java.util.Optional;

public class SpongeColorComponent implements ColorComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Color color) {
        ((SpongeItemStack) itemStack).getHandle().offer(Keys.COLOR, org.spongepowered.api.util.Color.of(color));
    }

    @Override
    public Optional<Color> getValue(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(Keys.COLOR)
                .map(org.spongepowered.api.util.Color::asJavaColor);
    }
}
