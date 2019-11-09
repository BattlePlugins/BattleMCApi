package mc.alk.mc.inventory;

import mc.alk.mc.MCPlatform;

import java.util.List;
import java.util.Map;

public interface MCItemStack {

    void setType(String type);

    String getType();

    void setDataValue(short value);

    short getDataValue();

    void setQuantity(int quantity);

    int getQuantity();

    Map<String, Integer> getEnchantments();

    void addEnchantment(String ench, int level);

    String getCommonName();

    String getFormattedCommonName();

    MCItemStack clone();

    int isSpecial();

    boolean hasItemMeta();

    MCItemMeta getItemMeta();

    static MCItemStack getDefaultPlatformItemStack() {
        return MCPlatform.getPlatform().getDefaultPlatformItemStack();
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder {

        private MCItemStack itemStack;

        Builder() {
            this.itemStack = MCItemStack.getDefaultPlatformItemStack();
        }

        public Builder fromItemStack(MCItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        public Builder type(String type) {
            itemStack.setType(type);
            return this;
        }

        public Builder data(short value) {
            itemStack.setDataValue(value);
            return this;
        }

        public Builder quantity(int quantity) {
            itemStack.setQuantity(quantity);
            return this;
        }

        public Builder enchant(String enchant, int level) {
            itemStack.addEnchantment(enchant, level);
            return this;
        }

        public Builder displayName(String displayName) {
            if (!itemStack.hasItemMeta())
                return this;

            itemStack.getItemMeta().setDisplayName(displayName);
            return this;
        }

        public Builder lore(List<String> lore) {
            if (!itemStack.hasItemMeta())
                return this;

            itemStack.getItemMeta().setLore(lore);
            return this;
        }

        public Builder unbreakable(boolean unbreakable) {
            if (!itemStack.hasItemMeta())
                return this;

            itemStack.getItemMeta().setUnbreakable(unbreakable);
            return this;
        }

        public Builder customModelData(int modelData) {
            if (!itemStack.hasItemMeta())
                return this;

            itemStack.getItemMeta().setCustomModelData(modelData);
            return this;
        }

        public MCItemStack build() {
            return itemStack;
        }
    }
}
