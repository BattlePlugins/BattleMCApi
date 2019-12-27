package org.battleplugins.api.inventory.item.component.flag;

/**
 * Holds all the valid ItemFlags.
 */
public class ItemFlags {

    /**
     * Hides the attributes on an item
     */
    public static final ItemFlag HIDE_ATTRIBUTES = new ItemFlag("hide_attributes");

    /**
     * Hides what blocks an item can destroy
     */
    public static final ItemFlag HIDE_DESTROYS = new ItemFlag("hide_destroys");

    /**
     * Hides the enchants on an item
     */
    public static final ItemFlag HIDE_ENCHANTS = new ItemFlag("hide_enchants");

    /**
     * Hides what blocks the item (block) can be placed on
     */
    public static final ItemFlag HIDE_PLACED_ON = new ItemFlag("hide_placed_on");

    /**
     * Hides the potion effects of an item
     */
    public static final ItemFlag HIDE_POTION_EFFECTS = new ItemFlag("hide_potion_effects");

    /**
     * Hides if the item is unbreakable
     */
    public static final ItemFlag HIDE_UNBREAKABLE = new ItemFlag("hide_unbreakable");

    /**
     * An array of all the {@link ItemFlag}s
     *
     * @return an array of all the item flags
     */
    public static ItemFlag[] values() {
        return new ItemFlag[] {HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS,
                    HIDE_PLACED_ON, HIDE_POTION_EFFECTS, HIDE_UNBREAKABLE};
    }
}
