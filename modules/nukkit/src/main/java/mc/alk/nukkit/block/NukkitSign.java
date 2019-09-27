package mc.alk.nukkit.block;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.nbt.tag.CompoundTag;

import mc.alk.mc.MCPlayer;
import mc.alk.mc.block.MCSign;
import mc.alk.nukkit.NukkitPlayer;

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
	public void sendSignChange(MCPlayer player, String[] lines) {
		CompoundTag nbt = new CompoundTag()
				.putString("id", BlockEntity.SIGN)
				.putInt("x", (int) sign.getLocation().x)
				.putInt("y", (int) sign.getLocation().y)
				.putInt("z", (int) sign.getLocation().z)
				.putString("Text1", "")
				.putString("Text2", "")
				.putString("Text3", "")
				.putString("Text4", "");

		BlockEntitySign sign = new BlockEntitySign(this.sign.getChunk(), nbt);
		sign.setText(lines);
		sign.spawnTo(((NukkitPlayer) player).getHandle());
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
	public void update() {
		sign.onUpdate();
		sign.getBlock().onUpdate(0);
	}

	@Override
	public void update(boolean force){
		sign.onUpdate();
		sign.getBlock().onUpdate(1);
	}
}
