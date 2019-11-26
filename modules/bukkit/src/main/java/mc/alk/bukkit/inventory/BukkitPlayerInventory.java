package mc.alk.bukkit.inventory;

import mc.alk.mc.inventory.MCPlayerInventory;

import org.bukkit.Material;
import org.bukkit.inventory.PlayerInventory;

import java.util.Optional;

public class BukkitPlayerInventory extends BukkitInventory implements MCPlayerInventory {

	private PlayerInventory inventory;

	public BukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);

		this.inventory = inventory;
	}

	@Override
	public Optional<BukkitItemStack> getItemInMainHand() {
		try {
			// 1.9+
			return Optional.ofNullable(inventory.getItemInMainHand()).map(BukkitItemStack::new);
		} catch (Throwable ex) {
			// pre 1.9
			return Optional.ofNullable(inventory.getItemInHand()).map(BukkitItemStack::new);
		}
	}

	@Override
	public Optional<BukkitItemStack> getItemInOffHand() {
		return Optional.ofNullable(inventory.getItemInOffHand()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<BukkitItemStack> getHelmet() {
		return Optional.ofNullable(inventory.getHelmet()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<BukkitItemStack> getChestplate() {
		return Optional.ofNullable(inventory.getChestplate()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<BukkitItemStack> getLeggings() {
		return Optional.ofNullable(inventory.getLeggings()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<BukkitItemStack> getBoots() {
		return Optional.ofNullable(inventory.getBoots()).map(BukkitItemStack::new);
	}
}
