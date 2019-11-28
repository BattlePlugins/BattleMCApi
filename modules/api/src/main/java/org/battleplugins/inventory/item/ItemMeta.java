package org.battleplugins.inventory.item;

import java.util.List;
import java.util.Optional;

public interface ItemMeta {

    default boolean hasDisplayName() {
        return getDisplayName().isPresent();
    }

    Optional<String> getDisplayName();
    void setDisplayName(String displayName);

    default boolean hasLore() {
        return getLore().isPresent();
    }

    Optional<List<String>> getLore();
    void setLore(List<String> lore);

    int getCustomModelData();
    void setCustomModelData(int modelData);

    boolean isUnbreakable();
    void setUnbreakable(boolean unbreakable);
}
