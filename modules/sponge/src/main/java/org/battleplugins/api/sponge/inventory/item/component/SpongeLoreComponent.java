package org.battleplugins.api.sponge.inventory.item.component;

import net.kyori.adventure.text.Component;
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
    public void applyComponent(ItemStack itemStack, List<Component> lore) {
        // TODO: API 8 - has full support for adventure
    }

    @Override
    public Optional<List<Component>> getValue(ItemStack itemStack) {
        return Optional.empty(); // TODO: API 8 - has full support for adventure
    }

    @Override
    public boolean isAppliable(ItemStack itemStack) {
        return true;
    }
}
