package org.battleplugins.nukkit.world.block.entity;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.nbt.tag.CompoundTag;

import org.battleplugins.entity.living.player.Player;
import org.battleplugins.nukkit.entity.living.player.NukkitPlayer;

public class NukkitSign extends NukkitBlockEntity<BlockEntitySign> implements org.battleplugins.world.block.entity.Sign {

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
