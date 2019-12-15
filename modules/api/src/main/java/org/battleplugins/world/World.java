package org.battleplugins.world;

import org.battleplugins.entity.living.player.Player;
import org.battleplugins.world.block.Block;
import org.battleplugins.world.block.entity.BlockEntity;

import java.util.Optional;

public interface World {

	String getName();

	Optional<? extends BlockEntity> getBlockEntityAt(Location loc);
	Block getBlockAt(Location loc);

	boolean isType(BlockEntity blockEntity, Class<? extends BlockEntity> clazz);
	<T extends BlockEntity> T toType(BlockEntity blockEntity, Class<T> clazz) throws ClassCastException;

	void sendBlockUpdate(Player player, Location location, Block block);
	void sendBlockEntityUpdate(Player player, Location location, BlockEntity blockEntity);
}
