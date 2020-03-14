package org.battleplugins.api.sponge.world.block;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.sponge.registry.inventory.SpongeItemRegistry;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.BlockType;
import org.spongepowered.api.data.property.block.HardnessProperty;
import org.spongepowered.api.item.ItemTypes;

public class SpongeBlockType extends MCWrapper<org.spongepowered.api.block.BlockType> implements BlockType {

    public SpongeBlockType(org.spongepowered.api.block.BlockType handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((SpongeItemRegistry) Platform.getRegistry().getItemRegistry()).fromPlatformItem(handle.getItem().orElse(ItemTypes.AIR));
    }

    @Override
    public float getHardness() {
        return handle.getProperty(HardnessProperty.class)
                .map(property -> property.getValue().floatValue())
                .orElse(0f);
    }
}
