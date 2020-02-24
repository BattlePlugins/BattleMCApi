package org.battleplugins.api.nukkit.world.block;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.battleplugins.api.nukkit.NukkitPlatform;
import org.battleplugins.api.world.block.BlockRegistry;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.block.BlockType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NukkitBlockRegistry implements BlockRegistry<Block> {

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
    public Optional<BlockType> fromKey(NamespacedKey namespacedKey) {
        Block block = IDENTIFIER_TO_BLOCK.get(namespacedKey.toString());
        if (block == null)
            return Optional.empty();

        return Optional.ofNullable(BLOCK_ENTRIES.get(block));
    }
}
