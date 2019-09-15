package mc.alk.nukkit.block;

import cn.nukkit.blockentity.BlockEntitySign;

import mc.alk.mc.block.MCSign;

public class NukkitSign extends NukkitBlock implements MCSign {

	private BlockEntitySign sign;

	public NukkitSign(BlockEntitySign sign) {
		super(sign.getBlock());

		this.sign = sign;
	}

	@Override
	public void setLine(int index, String line) {
		String[] text = sign.getText();
		text[index] = line;

		sign.setText(text);
		sign.onUpdate();
	}

	@Override
	public String getLine(int index) {
		return sign.getText()[index];
	}

	@Override
	public String[] getLines() {
		return sign.getText();
	}

	@Override
	public NukkitSign clone(){
		return new NukkitSign(sign);
	}

	@Override
	public void update(boolean b){
		sign.onUpdate();
		sign.getBlock().onUpdate(1);
	}
}
