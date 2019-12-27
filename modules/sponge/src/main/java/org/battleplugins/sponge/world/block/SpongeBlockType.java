package org.battleplugins.sponge.world.block;

import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.sponge.inventory.item.SpongeItemRegistry;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.block.BlockType;
import org.spongepowered.api.data.property.block.HardnessProperty;
import org.spongepowered.api.item.ItemTypes;

public class SpongeBlockType extends MCWrapper<org.spongepowered.api.block.BlockType> implements BlockType {

    SpongeBlockType(org.spongepowered.api.block.BlockType handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((SpongeItemRegistry) ItemRegistry.REGISTRY).fromPlatformItem(handle.getItem().orElse(ItemTypes.AIR));
    }

    @Override
    public float getHardness() {
        return handle.getProperty(HardnessProperty.class)
                .map(property -> property.getValue().floatValue())
                .orElse(0f);
    }
}
