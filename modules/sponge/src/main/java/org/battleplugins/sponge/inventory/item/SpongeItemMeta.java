package org.battleplugins.sponge.inventory.item;

import org.battleplugins.inventory.item.ItemMeta;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.DisplayNameData;
import org.spongepowered.api.data.manipulator.mutable.item.DurabilityData;
import org.spongepowered.api.data.manipulator.mutable.item.LoreData;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Due to how Sponge handles item metadata, we instead
 * pass a Sponge 'ItemStack' in the constructor.
 */
public class SpongeItemMeta implements ItemMeta {

    private ItemStack itemStack;

    public SpongeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public Optional<String> getDisplayName() {
        return itemStack.getOrCreate(DisplayNameData.class).map(data -> data.displayName().get().toPlain());
    }

    @Override
    public void setDisplayName(String displayName) {
        itemStack.offer(Keys.DISPLAY_NAME, Text.of(displayName));
    }

    @Override
    public Optional<List<String>> getLore() {
        return itemStack.getOrCreate(LoreData.class).map(data -> data.lore().get().stream().map(Text::toPlain).collect(Collectors.toList()));
    }

    @Override
    public void setLore(List<String> lore) {
        itemStack.offer(Keys.ITEM_LORE, lore.stream().map(Text::of).collect(Collectors.toList()));
    }

    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    public void setCustomModelData(int modelData) {
        // No support for this in Sponge... yet :)
    }

    @Override
    public boolean isUnbreakable() {
        return itemStack.getOrCreate(DurabilityData.class).map(DurabilityData::unbreakable).get().get();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.UNBREAKABLE, unbreakable);
    }
}
