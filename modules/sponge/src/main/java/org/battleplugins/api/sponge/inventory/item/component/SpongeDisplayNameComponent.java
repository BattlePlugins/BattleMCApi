package org.battleplugins.api.sponge.inventory.item.component;

import net.kyori.adventure.text.Component;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.component.DisplayNameComponent;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.DisplayNameData;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class SpongeDisplayNameComponent implements DisplayNameComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Component displayName) {
        // TODO: API 8 - has full support for adventure
    }

    @Override
    public Optional<Component> getValue(ItemStack itemStack) {
        return Optional.empty(); // TODO: API 8 - has full support for adventure
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return ((SpongeItemStack) itemStack).getHandle().get(DisplayNameData.class).isPresent();
    }
}
