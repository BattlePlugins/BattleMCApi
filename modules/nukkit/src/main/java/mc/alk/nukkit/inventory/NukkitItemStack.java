package mc.alk.nukkit.inventory;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

import mc.alk.mc.inventory.MCItemStack;
import mc.alk.nukkit.util.NukkitInventoryUtil;

import java.util.HashMap;
import java.util.Map;

public class NukkitItemStack implements MCItemStack {

	private Item itemStack;

	public NukkitItemStack(Item itemStack) {
		this.itemStack = itemStack == null ? new Item(0): itemStack;
	}

	@Override
	public void setType(String type) {
		// Nukkit doesn't have a setter for items.. so get from string instead
		Item item = Item.fromString(type);
		item.setCount(itemStack.getCount());
		item.setCompoundTag(itemStack.getCompoundTag());
		item.setCustomName(itemStack.getCustomName());
		item.setCustomBlockData(itemStack.getCustomBlockData());
		item.setDamage(itemStack.getDamage());
		item.setLore(itemStack.getLore());
		item.setNamedTag(itemStack.getNamedTag());

		this.itemStack = item;
	}

	@Override
	public String getType() {
		return NukkitInventoryUtil.getTypeFromId(itemStack.getId());
	}

	@Override
	public void setDataValue(short value) {
		itemStack.setDamage((int) value);
	}

	@Override
	public short getDataValue() {
		return (short) itemStack.getDamage();
	}

	@Override
	public void setQuantity(int quantity) {
		itemStack.setCount(quantity);
	}

	@Override
	public int getQuantity() {
		return itemStack.getCount();
	}

	@Override
	public Map<String, Integer> getEnchantments() {
		Map<String, Integer> encs = new HashMap<String,Integer>();
		for (Enchantment enchant : itemStack.getEnchantments()) {
			encs.put(enchant.getName(), enchant.getId());
		}
		return encs;
	}

	@Override
	public boolean hasMetaData() {
		return itemStack.hasMeta();
	}

	@Override
	public String getCommonName() {
		return itemStack.getName();
	}

	@Override
	public String getFormattedCommonName() {
		return NukkitInventoryUtil.getFormattedCommonName(itemStack);
	}

	@Override
	public MCItemStack clone(){
		return new NukkitItemStack(itemStack.clone());
	}

	@Override
	public String toString(){
		return itemStack != null ? "["+ itemStack.getName() +":"+itemStack.getDamage() + " q="+
				getQuantity()+"]" : "null";
	}

	@Override
	public void addEnchantment(String ench, int level) {
		for (Enchantment enchant : Enchantment.getEnchantments()) {
			if (!enchant.getName().equalsIgnoreCase(ench))
				continue;

			itemStack.addEnchantment(enchant);
			return;
		}
	}

	public void addEnchantment(Enchantment enc, int level) {
		itemStack.addEnchantment(enc);
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

	public Item getNukkitItem() {
		return itemStack;
	}
}
