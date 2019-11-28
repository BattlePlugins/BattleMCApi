package org.battleplugins.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.item.Item;

import org.battleplugins.nukkit.inventory.NukkitInventory;
import org.battleplugins.nukkit.inventory.item.NukkitItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NukkitChest extends NukkitBlockEntity<BlockEntityChest> implements org.battleplugins.world.block.entity.Chest {

	public NukkitChest(BlockEntityChest chest) {
		super(chest);
	}

	@Override
	public NukkitItemStack[] getItems() {
		List<Item> items = new ArrayList<>(handle.getInventory().getContents().values());
		NukkitItemStack[] mcItems = new NukkitItemStack[items.size()];
		for (int i = 0; i < items.size(); i++){
			mcItems[i] = new NukkitItemStack(items.get(i));
		}

		return mcItems;
	}

	@Override
	public boolean isDoubleChest() {
		return handle.isPaired();
	}

	@Override
	public Optional<NukkitChest> getNeighborChest() {
		return Optional.ofNullable(handle.getPair()).map(NukkitChest::new);
	}

	@Override
	public NukkitInventory getInventory() {
		return new NukkitInventory<>(handle.getInventory());
	}
}
