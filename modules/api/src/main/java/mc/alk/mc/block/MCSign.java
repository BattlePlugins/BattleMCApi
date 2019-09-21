package mc.alk.mc.block;

import mc.alk.mc.MCPlayer;

public interface MCSign extends MCBlock {

	String getLine(int index);
	String[] getLines();

	void setLine(int index, String msg);

	void sendSignChange(MCPlayer player, String[] lines);
}
