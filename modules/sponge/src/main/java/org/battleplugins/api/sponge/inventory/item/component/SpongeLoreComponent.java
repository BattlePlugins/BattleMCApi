package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.LoreComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpongeLoreComponent implements LoreComponent {

    @Override
    public void applyComponent(ItemStack itemStack, List<String> lore) {
        ((SpongeItemStack) itemStack).getHandle().offer(Keys.ITEM_LORE,
                lore.stream().map(Text::of).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<String>> getValue(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(Keys.ITEM_LORE)
                .map(val -> val.stream().map(Text::toPlain).collect(Collectors.toList()));
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
