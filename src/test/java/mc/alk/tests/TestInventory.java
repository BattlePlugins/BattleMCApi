package mc.alk.tests;

import java.util.Comparator;
import java.util.TreeMap;

import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;

import org.apache.commons.lang.StringUtils;

public class TestInventory implements MCInventory{
	TreeMap<MCItemStack, MCItemStack> items = new TreeMap<MCItemStack,MCItemStack>(
			new Comparator<MCItemStack>(){
		@Override
		public int compare(MCItemStack arg0, MCItemStack arg1) {
			int c = new Integer(arg0.getType()).compareTo(arg1.getType());
			if (c!= 0)
				return c;
			c = new Short(arg0.getDataValue()).compareTo(arg1.getDataValue());
			if (c!= 0)
				return c;
			return new Integer(arg0.getEnchantments().size()).compareTo(arg1.getEnchantments().size());
		}
	});

	public void addItem(MCItemStack...itemStacks) {
		for (MCItemStack item: itemStacks){
			addItem(item);
		}
	}

	public void addItem(MCItemStack itemStack) {
		MCItemStack i = items.get(itemStack);
		if (i != null){
			i.setQuantity(itemStack.getQuantity() + i.getQuantity());
		} else {
			items.put(itemStack, itemStack);
		}
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		MCItemStack i = items.get(itemStack);
		if (i != null){
			if (i.getQuantity() - itemStack.getQuantity() < 0){
				items.remove(itemStack);
			} else {
				i.setQuantity(i.getQuantity() - itemStack.getQuantity());
			}
		}
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		MCItemStack i = items.get(itemStack);
		return i != null ? i.getQuantity() : 0;
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		return items.size() < 27;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return items.size() >= 27 ? -1 : 64 - itemStack.getQuantity();
	}
	@Override
	public String toString(){
		return "[Inventory " + StringUtils.join(items.keySet(), ", ") +"]";
	}

	@Override
	public MCItemStack[] getContents() {
		return items.keySet().toArray(new MCItemStack[items.size()]);
	}

}
