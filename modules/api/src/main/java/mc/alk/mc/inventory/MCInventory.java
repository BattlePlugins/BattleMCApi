package mc.alk.mc.inventory;

import java.util.Optional;

public interface MCInventory {

	void addItem(MCItemStack...itemStacks);
	void removeItem(MCItemStack itemStack);

	void setItem(int slot, MCItemStack item);
	Optional<? extends MCItemStack> getItem(int slot);

	int getItemAmount(MCItemStack itemStack);
	int freeSpaceAfter(MCItemStack itemStack);

	boolean canFit(MCItemStack itemStack);

	MCItemStack[] getContents();

	default void setContents(MCItemStack[] contents) {
		for (int i = 0; i < contents.length; i++) {
			setItem(i, contents[i]);
		}
	}

	void clear();
}
