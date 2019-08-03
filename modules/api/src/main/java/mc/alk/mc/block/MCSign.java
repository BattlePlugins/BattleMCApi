package mc.alk.mc.block;

public interface MCSign extends MCBlock {

	String getLine(int index);
	String[] getLines();

	void setLine(int index, String msg);
}
