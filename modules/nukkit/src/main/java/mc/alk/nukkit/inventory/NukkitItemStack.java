package mc.alk.nukkit.inventory;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.util.MCWrapper;
import mc.alk.nukkit.util.NukkitInventoryUtil;

import java.util.HashMap;
import java.util.Map;

public class NukkitItemStack extends MCWrapper<Item> implements MCItemStack {

	public NukkitItemStack(Item itemStack) {
		super(itemStack == null ? new Item(0): itemStack);
	}

	@Override
	public void setType(String type) {
		// Nukkit doesn't have a setter for items.. so get from string instead
		Item item = Item.fromString(type);
		item.setCount(handle.getCount());
		item.setCompoundTag(handle.getCompoundTag());
		item.setCustomName(handle.getCustomName());
		item.setCustomBlockData(handle.getCustomBlockData());
		item.setDamage(handle.getDamage());
		item.setLore(handle.getLore());
		item.setNamedTag(handle.getNamedTag());

		this.handle = item;
	}

	@Override
	public String getType() {
		return NukkitInventoryUtil.getTypeFromId(handle.getId());
	}

	@Override
	public void setDataValue(short value) {
		handle.setDamage((int) value);
	}

	@Override
	public short getDataValue() {
		return (short) handle.getDamage();
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
		Map<String, Integer> encs = new HashMap<String,Integer>();
		for (Enchantment enchant : handle.getEnchantments()) {
			encs.put(enchant.getName(), enchant.getId());
		}
		return encs;
	}

	@Override
	public boolean hasMetaData() {
		return handle.hasMeta();
	}

	@Override
	public String getCommonName() {
		return handle.getName();
	}

	@Override
	public String getFormattedCommonName() {
		return NukkitInventoryUtil.getFormattedCommonName(handle);
	}

	@Override
	public NukkitItemStack clone(){
		return new NukkitItemStack(handle.clone());
	}

	@Override
	public String toString(){
		return handle != null ? "["+ handle.getName() +":"+handle.getDamage() + " q="+
				getQuantity()+"]" : "null";
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

	@Override
	public int isSpecial() {
		int special = 0;
//		ItemMeta im = itemStack.getItemMeta();
//		Map<Enchantment,Integer> map = itemStack.getEnchantments();
//		if (map != null){
//			for (Entry<Enchantment,Integer> entry : map.entrySet()){
////				entry.getKey().getId()
//			}
//		}

		return special;
	}
}
