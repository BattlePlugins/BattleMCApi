package org.battleplugins.api.nukkit.registry.world;

import cn.nukkit.block.Block;

import org.battleplugins.api.nukkit.world.block.NukkitBlockType;
import org.battleplugins.api.registry.world.BlockRegistry;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.BlockType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NukkitBlockRegistry extends BlockRegistry<Block> {

    private static final Map<Block, NukkitBlockType> BLOCK_ENTRIES = new HashMap<>();
    private static final Map<String, Block> IDENTIFIER_TO_BLOCK = new HashMap<>();

    public NukkitBlockRegistry() {
        // TODO: Rework block registry when nukkit 2.0 is released
        /*
        InputStream inputStream = NukkitPlatform.class.getResourceAsStream("/mappings/blocks.json");
        if (inputStream == null)
            throw new AssertionError("Blocks Table not found");

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, BlockEntry>>() {}.getType();
        Map<String, BlockEntry> blockEntries = gson.fromJson(new InputStreamReader(inputStream), mapType);
        for (Map.Entry<String, BlockEntry> blockEntry : blockEntries.entrySet()) {
            Item item = Item.fromString(blockEntry.getValue().getBedrockIdentifier() + ":"
                        + blockEntry.getValue().getBedrockData());

            Block block = Block.get(item.getId(), item.getDamage());
            BLOCK_ENTRIES.put(block, new NukkitBlockType(block));
            IDENTIFIER_TO_BLOCK.put(blockEntry.getKey(), block);
        }

         */
    }

    @Override
    public BlockType fromPlatformBlock(Block block) {
        return BLOCK_ENTRIES.get(block);
    }

    @Override
    public Optional<BlockType> fromIdentifier(Identifier identifier) {
        Block block = IDENTIFIER_TO_BLOCK.get(identifier.toString());
        if (block == null)
            return Optional.empty();

        return Optional.ofNullable(BLOCK_ENTRIES.get(block));
    }
}
