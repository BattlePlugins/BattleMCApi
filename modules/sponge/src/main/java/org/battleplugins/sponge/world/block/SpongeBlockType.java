package org.battleplugins.sponge.world.block;

import org.battleplugins.sponge.inventory.item.SpongeItemType;
import org.battleplugins.world.block.BlockType;
import org.spongepowered.api.data.property.block.HardnessProperty;
import org.spongepowered.api.item.ItemTypes;

public class SpongeBlockType extends SpongeItemType implements BlockType {

    org.spongepowered.api.block.BlockType blockType;
    protected SpongeBlockType(org.spongepowered.api.block.BlockType blockType) {
        super(blockType.getItem().orElse(ItemTypes.AIR));

        this.blockType = blockType;
    }

    @Override
    public float getHardness() {
        return blockType.getProperty(HardnessProperty.class)
                .map(property -> property.getValue().floatValue())
                .orElse(0f);
    }
}
