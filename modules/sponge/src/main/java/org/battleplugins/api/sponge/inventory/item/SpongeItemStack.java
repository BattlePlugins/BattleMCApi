package org.battleplugins.api.sponge.inventory.item;

import org.battleplugins.api.util.MCWrapper;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.manipulator.mutable.item.EnchantmentData;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.enchantment.Enchantment;
import org.spongepowered.api.item.enchantment.EnchantmentType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpongeItemStack extends MCWrapper<ItemStack> implements org.battleplugins.api.inventory.item.ItemStack {

    public SpongeItemStack(ItemStack itemStack) {
        super(itemStack == null ? ItemStack.of(ItemTypes.AIR): itemStack);
    }

    @Override
    public void setType(org.battleplugins.api.inventory.item.ItemType type) {
        Optional<ItemType> opItem = Sponge.getGame().getRegistry().getType(ItemType.class, type.getIdentifier().toString());
        if (!opItem.isPresent())
            return;

        this.handle = ItemStack.builder().itemType(opItem.get()).build();
    }

    @Override
    public org.battleplugins.api.inventory.item.ItemType getType() {
        return new SpongeItemType(handle.getType());
    }

    @Override
    public void setQuantity(int quantity) {
        this.handle = ItemStack.builder().fromItemStack(handle).quantity(quantity).build();
    }

    @Override
    public int getQuantity() {
        return handle.getQuantity();
    }

    @Override
    public Map<String, Integer> getEnchantments() {
        Map<String, Integer> enchants = new HashMap<>();
        EnchantmentData data = handle.getOrCreate(EnchantmentData.class).get();
        for (Enchantment ench : data.enchantments()) {
            enchants.put(ench.getType().getName(), ench.getLevel());
        }

        return enchants;
    }

    @Override
    public void addEnchantment(String ench, int level) {
        Optional<EnchantmentType> opEnchant = Sponge.getGame().getRegistry().getType(EnchantmentType.class, ench);
        if (!opEnchant.isPresent())
            return;

        EnchantmentData data = handle.getOrCreate(EnchantmentData.class).get();
        data.set(data.enchantments().add(Enchantment.builder().type(opEnchant.get()).level(level).build()));
        handle.offer(data);
    }

    @Override
    public SpongeItemStack clone() {
        return new SpongeItemStack(handle.copy());
    }
}
