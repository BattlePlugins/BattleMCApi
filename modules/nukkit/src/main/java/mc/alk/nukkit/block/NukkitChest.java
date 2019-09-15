package mc.alk.nukkit.block;

import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.item.Item;

import mc.alk.mc.block.MCChest;
import mc.alk.nukkit.inventory.NukkitInventory;
import mc.alk.nukkit.inventory.NukkitItemStack;

import java.util.ArrayList;
import java.util.List;

public class NukkitChest extends NukkitBlock implements MCChest {

	private BlockEntityChest chest;

	public NukkitChest(BlockEntityChest chest) {
		super(chest.getBlock());

		this.chest = chest;
	}

	@Override
	public NukkitItemStack[] getItems() {
		List<Item> items = new ArrayList<>(chest.getInventory().getContents().values());
		NukkitItemStack[] mcItems = new NukkitItemStack[items.size()];
		for (int i = 0; i < items.size(); i++){
			mcItems[i] = new NukkitItemStack(items.get(i));
		}

		return mcItems;
	}

	@Override
	public boolean isDoubleChest() {
		return chest.isPaired();
	}

	@Override
	public NukkitChest getNeighborChest() {
		return new NukkitChest(chest.getPair());
	}

	@Override
	public NukkitInventory getInventory() {
		return new NukkitInventory(chest.getInventory());
	}
}
