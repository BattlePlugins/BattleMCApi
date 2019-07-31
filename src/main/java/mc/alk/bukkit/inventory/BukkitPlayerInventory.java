package mc.alk.bukkit.inventory;

import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventory extends BukkitInventory {

	private PlayerInventory inventory;

	public BukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);
		this.inventory = inventory;
	}

	@Override
	public PlayerInventory getBukkitInventory() {
		return inventory;
	}
}
