package org.battleplugins.inventory.item;

import org.battleplugins.Platform;
import org.battleplugins.inventory.item.component.ItemComponent;

import java.util.Map;
import java.util.Optional;

/**
 * Represents items in inventories.
 */
public interface ItemStack {

    /**
     * The {@link ItemType} for this itemstack
     *
     * @return the itemtype to set
     */
    ItemType getType();

    /**
     * Sets the {@link ItemType} for this itemstack
     *
     * @param type the itemtype to set
     */
    void setType(ItemType type);

    /**
     * The quantity of items in this itemstack
     *
     * @return the quantity of items in this itemstack
     */
    int getQuantity();

    /**
     * Sets the quantity of items in this itemstack
     *
     * @param quantity the quantity of items in this itemstack
     */
    void setQuantity(int quantity);

    /**
     * The enchantments applied to this itemstack
     *
     * @return the enchantments applied to this itemstack
     */
    Map<String, Integer> getEnchantments();

    /**
     * Adds an enchantment to this itemstack
     *
     * @param enchantment the enchantment to apply
     * @param level the level to set for the itemstack
     */
    void addEnchantment(String enchantment, int level);

    /**
     * Applies the given component to the itemstack
     *
     * @param componentClass the component to apply
     * @param value the value of the component
     * @param <T> the value
     */
    default <T> void applyComponent(Class<? extends ItemComponent<T>> componentClass, T value) {
        Platform.getPlatform().getRegistry().getItemComponent(componentClass).applyComponent(this, value);
    }

    /**
     * Gets the value of the given component class
     *
     * @param componentClass the class of the component
     * @param <T> the value
     * @return the value of the given component class
     */
    default <T> Optional<T> getValue(Class<? extends ItemComponent<T>> componentClass) {
        return Platform.getPlatform().getRegistry().getItemComponent(componentClass).getValue(this);
    }

    /**
     * The itemstack builder
     *
     * @return a new itemstack builder
     */
    static Builder builder() {
        return new Builder();
    }

    ItemStack clone();

    class Builder {

        private ItemStack itemStack;

        Builder() {
            this.itemStack = Platform.getPlatform().getDefaultPlatformItemStack();
        }

        public Builder fromItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        public Builder type(ItemType type) {
            itemStack.setType(type);
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

        public <T> Builder component(Class<? extends ItemComponent<T>> component, T value) {
            itemStack.applyComponent(component, value);
            return this;
        }

        public ItemStack build() {
            return itemStack;
        }
    }
}
