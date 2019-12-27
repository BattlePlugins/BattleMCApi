package org.battleplugins.api.sponge.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DisplayNameComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class SpongeDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, String displayName) {
        ((SpongeItemStack) itemStack).getHandle().offer(Keys.DISPLAY_NAME, Text.of(displayName));
    }

    @Override
    public Optional<String> getValue(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(Keys.DISPLAY_NAME).map(Text::toPlain);
    }
}
