package org.battleplugins.sponge.inventory.item.component;

import org.battleplugins.inventory.item.ItemStack;
import org.battleplugins.inventory.item.component.ItemFlagComponent;
import org.battleplugins.inventory.item.component.flag.ItemFlag;
import org.battleplugins.inventory.item.component.flag.ItemFlags;
import org.battleplugins.sponge.inventory.item.SpongeItemStack;
import org.spongepowered.api.data.key.Keys;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SpongeItemFlagComponent implements ItemFlagComponent {

    @Override
    public void applyComponent(ItemStack itemStack, Set<ItemFlag> itemFlags) {
        org.spongepowered.api.item.inventory.ItemStack spongeItemStack = ((SpongeItemStack) itemStack).getHandle();
        for (ItemFlag flag : itemFlags) {
            if (flag.equals(ItemFlags.HIDE_ATTRIBUTES)) {
                spongeItemStack.offer(Keys.HIDE_ATTRIBUTES, true);
            }

            if (flag.equals(ItemFlags.HIDE_DESTROYS)) {
                spongeItemStack.offer(Keys.HIDE_CAN_DESTROY, true);
            }

            if (flag.equals(ItemFlags.HIDE_ENCHANTS)) {
                spongeItemStack.offer(Keys.HIDE_ENCHANTMENTS, true);
            }

            if (flag.equals(ItemFlags.HIDE_PLACED_ON)) {
                spongeItemStack.offer(Keys.HIDE_CAN_PLACE, true);
            }

            if (flag.equals(ItemFlags.HIDE_POTION_EFFECTS)) {
                spongeItemStack.offer(Keys.HIDE_MISCELLANEOUS, true);
            }

            if (flag.equals(ItemFlags.HIDE_UNBREAKABLE)) {
                spongeItemStack.offer(Keys.HIDE_UNBREAKABLE, true);
            }
        }
    }

    @Override
    public Optional<Set<ItemFlag>> getValue(ItemStack itemStack) {
        org.spongepowered.api.item.inventory.ItemStack spongeItemStack = ((SpongeItemStack) itemStack).getHandle();
        Set<ItemFlag> itemFlags = new HashSet<>();
        if (spongeItemStack.get(Keys.HIDE_ATTRIBUTES).isPresent())
            itemFlags.add(ItemFlags.HIDE_ATTRIBUTES);

        if (spongeItemStack.get(Keys.HIDE_CAN_DESTROY).isPresent())
            itemFlags.add(ItemFlags.HIDE_DESTROYS);

        if (spongeItemStack.get(Keys.HIDE_ENCHANTMENTS).isPresent())
            itemFlags.add(ItemFlags.HIDE_ENCHANTS);

        if (spongeItemStack.get(Keys.HIDE_CAN_PLACE).isPresent())
            itemFlags.add(ItemFlags.HIDE_PLACED_ON);

        if (spongeItemStack.get(Keys.HIDE_MISCELLANEOUS).isPresent())
            itemFlags.add(ItemFlags.HIDE_POTION_EFFECTS);

        if (spongeItemStack.get(Keys.HIDE_UNBREAKABLE).isPresent())
            itemFlags.add(ItemFlags.HIDE_UNBREAKABLE);

        return itemFlags.isEmpty() ? Optional.empty() : Optional.of(itemFlags);
    }
}
