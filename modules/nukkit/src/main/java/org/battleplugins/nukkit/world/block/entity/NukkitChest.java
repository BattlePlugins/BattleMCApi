package org.battleplugins.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntityChest;

import org.battleplugins.inventory.CarriedInventory;
import org.battleplugins.inventory.carrier.Carrier;
import org.battleplugins.nukkit.inventory.NukkitCarriedInventory;

import java.util.Optional;

public class NukkitChest extends NukkitBlockEntity<BlockEntityChest> implements org.battleplugins.world.block.entity.Chest {

	public NukkitChest(BlockEntityChest chest) {
		super(chest);
	}

	@Override
	public Optional<NukkitChest> getNeighborChest() {
		return Optional.ofNullable(handle.getPair()).map(NukkitChest::new);
	}

	@Override
	public CarriedInventory<NukkitChest> getInventory() {
		return new NukkitCarriedInventory<>(handle.getInventory(), this);
	}
}
