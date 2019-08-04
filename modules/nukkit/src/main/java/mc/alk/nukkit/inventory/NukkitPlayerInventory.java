package mc.alk.nukkit.inventory;

import cn.nukkit.inventory.PlayerInventory;

public class NukkitPlayerInventory extends NukkitInventory {

	private PlayerInventory inventory;

	public NukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public PlayerInventory getNukkitInventory() {
		return inventory;
	}
}
