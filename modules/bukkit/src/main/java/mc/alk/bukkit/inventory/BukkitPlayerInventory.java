package mc.alk.bukkit.inventory;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.inventory.MCPlayerInventory;

import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventory extends BukkitInventory implements MCPlayerInventory {

	private PlayerInventory inventory;

	public BukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public MCItemStack getItemInMainHand() {
		return new BukkitItemStack(inventory.getItemInMainHand());
	}

	@Override
	public MCItemStack getItemInOffHand() {
		return new BukkitItemStack(inventory.getItemInOffHand());
	}

	@Override
	public MCItemStack getHelmet() {
		return new BukkitItemStack(inventory.getHelmet());
	}

	@Override
	public MCItemStack getChestplate() {
		return new BukkitItemStack(inventory.getChestplate());
	}

	@Override
	public MCItemStack getLeggings() {
		return new BukkitItemStack(inventory.getLeggings());
	}

	@Override
	public MCItemStack getBoots() {
		return new BukkitItemStack(inventory.getBoots());
	}

	@Override
	public PlayerInventory getBukkitInventory() {
		return inventory;
	}
}
