package mc.alk.mc.inventory;

import java.util.List;

public class ItemBuilder {

    private MCItemStack itemStack;

    public ItemBuilder() {
        this.itemStack = MCItemStack.getDefaultPlatformItemStack();
    }

    public ItemBuilder(MCItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(String material) {
        this.itemStack = MCItemStack.getDefaultPlatformItemStack();
        this.itemStack.setType(material);
    }

    public ItemBuilder setType(String type) {
        itemStack.setType(type);
        return this;
    }

    public ItemBuilder setData(short value) {
        itemStack.setDataValue(value);
        return this;
    }

    public ItemBuilder setQuantity(int quantity) {
        itemStack.setQuantity(quantity);
        return this;
    }

    public ItemBuilder addEnchantment(String enchant, int level) {
        itemStack.addEnchantment(enchant, level);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        if (!itemStack.hasItemMeta())
            return this;

        itemStack.getItemMeta().setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (!itemStack.hasItemMeta())
            return this;

        itemStack.getItemMeta().setLore(lore);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        if (!itemStack.hasItemMeta())
            return this;

        itemStack.getItemMeta().setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setCustomModelData(int modelData) {
        if (!itemStack.hasItemMeta())
            return this;

        itemStack.getItemMeta().setCustomModelData(modelData);
        return this;
    }

    public MCItemStack build() {
        return itemStack;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }
}
