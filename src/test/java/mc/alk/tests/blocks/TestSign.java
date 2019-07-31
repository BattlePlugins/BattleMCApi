package mc.alk.tests.blocks;

import mc.alk.mc.MCLocation;
import mc.alk.mc.block.MCSign;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;

import mc.alk.tests.TestBlock;

public class TestSign extends TestBlock implements MCSign{
	String[] lines = new String[4];

	public TestSign(MCLocation loc, String[] lines) {
		super(loc, Material.SIGN.getId());
		this.lines = lines;
	}

	@Override
	public void setLine(int i, String msg) {
		lines[i] = msg;
	}

	@Override
	public String getLine(int i) {
		return lines[i];
	}

	@Override
	public void update(boolean b) {

	}

	@Override
	public String[] getLines() {
		return lines;
	}
	@Override
	public String toString(){
		return "["+super.toString() +"," + (lines != null ? StringUtils.join(lines,","): "null")+"]";
	}
}
