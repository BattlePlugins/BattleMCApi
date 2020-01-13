package org.battleplugins.api.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntityChest;

import org.battleplugins.api.nukkit.inventory.NukkitCarriedInventory;
import org.battleplugins.api.world.block.entity.Chest;
import org.battleplugins.api.inventory.CarriedInventory;

import java.util.Optional;

public class NukkitChest extends NukkitBlockEntity<BlockEntityChest> implements Chest {

	public NukkitChest(BlockEntityChest chest) {
		super(chest);
	}

	@Override
	public Optional<org.battleplugins.api.world.block.entity.Chest> getNeighborChest() {
		return Optional.ofNullable(handle.getPair()).map(NukkitChest::new);
	}

	@Override
	public CarriedInventory<NukkitChest> getInventory() {
		return new NukkitCarriedInventory<>(handle.getInventory(), this);
	}
}
