package org.battleplugins.sponge.inventory.item;

import org.battleplugins.inventory.item.ItemMeta;
import org.battleplugins.sponge.util.SpongeInventoryUtil;
import org.battleplugins.util.MCWrapper;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.item.EnchantmentData;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.enchantment.Enchantment;
import org.spongepowered.api.item.enchantment.EnchantmentType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpongeItemStack extends MCWrapper<ItemStack> implements org.battleplugins.inventory.item.ItemStack {

    public SpongeItemStack(ItemStack itemStack) {
        super(itemStack == null ? ItemStack.of(ItemTypes.AIR): itemStack);
    }

    @Override
    public void setType(String type) {
        Optional<ItemType> opItem = Sponge.getGame().getRegistry().getType(ItemType.class, "minecraft:" + type);
        if (!opItem.isPresent())
            return;

        this.handle = ItemStack.builder().itemType(opItem.get()).build();
    }

    @Override
    public String getType() {
        return handle.getType().getName();
    }

    @Override
    public void setDataValue(short value) {
        handle.offer(Keys.ITEM_DURABILITY, (int) value);
    }

    @Override
    public short getDataValue() {
        return handle.get(Keys.ITEM_DURABILITY).orElse(0).shortValue();
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
        Map<String, Integer> enchants = new HashMap<String, Integer>();
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
    public String getCommonName() {
        return handle.getType().getName();
    }

    @Override
    public String getFormattedCommonName() {
        return SpongeInventoryUtil.getFormattedCommonName(handle);
    }

    @Override
    public SpongeItemStack clone() {
        return new SpongeItemStack(handle.copy());
    }

    @Override
    public int isSpecial() {
        return 0;
    }

    @Override
    public boolean hasItemMeta() {
        return true;
    }

    @Override
    public ItemMeta getItemMeta() {
        return new SpongeItemMeta(handle);
    }
}
