package mc.alk.sponge.inventory;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.sponge.util.SpongeInventoryUtil;

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

public class SpongeItemStack implements MCItemStack {

    private ItemStack itemStack;

    public SpongeItemStack(ItemStack itemStack) {
        this.itemStack = itemStack == null ? ItemStack.of(ItemTypes.AIR): itemStack;
    }

    @Override
    public void setType(String type) {
        Optional<ItemType> opItem = Sponge.getGame().getRegistry().getType(ItemType.class, type);
        if (!opItem.isPresent())
            return;

        this.itemStack = ItemStack.builder().fromItemStack(itemStack).itemType(opItem.get()).build();
    }

    @Override
    public String getType() {
        return itemStack.getType().getName();
    }

    @Override
    public void setDataValue(short value) {
        itemStack.offer(Keys.ITEM_DURABILITY, (int) value);
    }

    @Override
    public short getDataValue() {
        return itemStack.get(Keys.ITEM_DURABILITY).get().shortValue();
    }

    @Override
    public void setQuantity(int quantity) {
        this.itemStack = ItemStack.builder().fromItemStack(itemStack).quantity(quantity).build();
    }

    @Override
    public int getQuantity() {
        return itemStack.getQuantity();
    }

    @Override
    public Map<String, Integer> getEnchantments() {
        Map<String, Integer> enchants = new HashMap<String, Integer>();
        EnchantmentData data = itemStack.getOrCreate(EnchantmentData.class).get();
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

        EnchantmentData data = itemStack.getOrCreate(EnchantmentData.class).get();
        data.set(data.enchantments().add(Enchantment.builder().type(opEnchant.get()).level(level).build()));
        itemStack.offer(data);
    }

    @Override
    public boolean hasMetaData() {
        return !itemStack.getKeys().isEmpty();
    }

    @Override
    public String getCommonName() {
        return itemStack.getType().getName();
    }

    @Override
    public String getFormattedCommonName() {
        return SpongeInventoryUtil.getFormattedCommonName(itemStack);
    }

    @Override
    public MCItemStack clone() {
        return new SpongeItemStack(itemStack);
    }

    @Override
    public int isSpecial() {
        return 0;
    }

    public ItemStack getSpongeItemStack() {
        return itemStack;
    }
}
