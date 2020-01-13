package org.battleplugins.api.bukkit.inventory.entity;

import org.battleplugins.api.bukkit.inventory.BukkitInventory;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.inventory.item.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Optional;

public class BukkitPlayerInventory extends BukkitInventory<PlayerInventory> implements org.battleplugins.api.inventory.entity.PlayerInventory {

	private Human carrier;
	private PlayerInventory inventory;

	public BukkitPlayerInventory(Human carrier, PlayerInventory inventory) {
		super(inventory);

		this.carrier = carrier;
		this.inventory = inventory;
	}

	@Override
	public Optional<ItemStack> getItemInMainHand() {
		try {
			// 1.9+
			return Optional.ofNullable(inventory.getItemInMainHand()).map(BukkitItemStack::new);
		} catch (Throwable ex) {
			// pre 1.9
			return Optional.ofNullable(inventory.getItemInHand()).map(BukkitItemStack::new);
		}
	}

	@Override
	public Optional<ItemStack> getItemInOffHand() {
		return Optional.ofNullable(inventory.getItemInOffHand()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getHelmet() {
		return Optional.ofNullable(inventory.getHelmet()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getChestplate() {
		return Optional.ofNullable(inventory.getChestplate()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getLeggings() {
		return Optional.ofNullable(inventory.getLeggings()).map(BukkitItemStack::new);
	}

	@Override
	public Optional<ItemStack> getBoots() {
		return Optional.ofNullable(inventory.getBoots()).map(BukkitItemStack::new);
	}

	@Override
	public Human getCarrier() {
		return carrier;
	}
}
