package mc.alk.mc.inventory;

public interface MCInventory {

	void addItem(MCItemStack...itemStacks);
	void removeItem(MCItemStack itemStack);

	int getItemAmount(MCItemStack itemStack);
	int freeSpaceAfter(MCItemStack itemStack);

	boolean canFit(MCItemStack itemStack);

	MCItemStack[] getContents();
}
