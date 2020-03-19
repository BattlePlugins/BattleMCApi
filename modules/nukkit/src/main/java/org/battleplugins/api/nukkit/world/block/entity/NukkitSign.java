package org.battleplugins.api.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntitySign;

import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;
import org.battleplugins.api.world.block.entity.Sign;

public class NukkitSign extends NukkitBlockEntity<BlockEntitySign> implements Sign {

	public NukkitSign(BlockEntitySign sign) {
		super(sign);
	}

	@Override
	public void setLine(int index, String line) {
		String[] text = handle.getText();
		text[index] = line;

		handle.setText(text);
		handle.scheduleUpdate();
	}

	@Override
	public void sendSignUpdate(Player player, String[] lines) {
		// TODO: Nukkit 2.0 : ((NukkitPlayer) player).getHandle().sendSignChange(handle.getLocation(), lines);
	}

	@Override
	public String getLine(int index) {
		return handle.getText()[index];
	}

	@Override
	public String[] getLines() {
		return handle.getText();
	}

	@Override
	public NukkitSign clone(){
		return new NukkitSign(handle);
	}
}
