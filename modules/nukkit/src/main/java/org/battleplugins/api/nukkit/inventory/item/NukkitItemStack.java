package org.battleplugins.api.nukkit.inventory.item;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.ItemRegistry;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.MCWrapper;

import java.util.HashMap;
import java.util.Map;

public class NukkitItemStack extends MCWrapper<Item> implements ItemStack {

	public NukkitItemStack(Item itemStack) {
		super(itemStack == null ? new Item(0): itemStack);
	}

	@Override
	public void setType(ItemType type) {
		// Nukkit doesn't have a setter for items.. so get from string instead
		Item item = ((NukkitItemType) type).getHandle();
		item.setCount(handle.getCount());
		item.setCompoundTag(handle.getCompoundTag());
		item.setCustomName(handle.getCustomName());

		if (handle.hasCustomBlockData())
			item.setCustomBlockData(handle.getCustomBlockData());

		if (handle.hasMeta())
			item.setDamage(handle.getDamage());

		if (handle.getLore() != null)
			item.setLore(handle.getLore());

		if (handle.getNamedTag() != null)
			item.setNamedTag(handle.getNamedTag());

		this.handle = item;
	}

	@Override
	public ItemType getType() {
		return ((NukkitItemRegistry) Platform.getPlatform().getRegistry().getItemRegistry()).fromPlatformItem(handle);
	}

	@Override
	public void setQuantity(int quantity) {
		handle.setCount(quantity);
	}

	@Override
	public int getQuantity() {
		return handle.getCount();
	}

	@Override
	public Map<String, Integer> getEnchantments() {
		Map<String, Integer> encs = new HashMap<>();
		for (Enchantment enchant : handle.getEnchantments()) {
			encs.put(enchant.getName(), enchant.getId());
		}
		return encs;
	}

	@Override
	public NukkitItemStack clone(){
		return new NukkitItemStack(handle.clone());
	}

	@Override
	public void addEnchantment(String ench, int level) {
		for (Enchantment enchant : Enchantment.getEnchantments()) {
			if (!enchant.getName().equalsIgnoreCase(ench))
				continue;

			handle.addEnchantment(enchant);
			return;
		}
	}
}
