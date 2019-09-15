package mc.alk.bukkit.inventory;

import mc.alk.mc.inventory.MCPlayerInventory;

import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventory extends BukkitInventory implements MCPlayerInventory {

	private PlayerInventory inventory;

	public BukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public BukkitItemStack getItemInMainHand() {
		try {
			// 1.9+
			return new BukkitItemStack(inventory.getItemInMainHand());
		} catch (Throwable ex) {
			// pre 1.9
			return new BukkitItemStack(inventory.getItemInHand());
		}
	}

	@Override
	public BukkitItemStack getItemInOffHand() {
		return new BukkitItemStack(inventory.getItemInOffHand());
	}

	@Override
	public BukkitItemStack getHelmet() {
		return new BukkitItemStack(inventory.getHelmet());
	}

	@Override
	public BukkitItemStack getChestplate() {
		return new BukkitItemStack(inventory.getChestplate());
	}

	@Override
	public BukkitItemStack getLeggings() {
		return new BukkitItemStack(inventory.getLeggings());
	}

	@Override
	public BukkitItemStack getBoots() {
		return new BukkitItemStack(inventory.getBoots());
	}
}
