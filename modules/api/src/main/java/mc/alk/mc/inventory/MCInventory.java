package mc.alk.mc.inventory;

public interface MCInventory {

	void addItem(MCItemStack...itemStacks);
	void removeItem(MCItemStack itemStack);

	void setItem(int slot, MCItemStack item);
	MCItemStack getItem(int slot);

	int getItemAmount(MCItemStack itemStack);
	int freeSpaceAfter(MCItemStack itemStack);

	boolean canFit(MCItemStack itemStack);

	MCItemStack[] getContents();
}
