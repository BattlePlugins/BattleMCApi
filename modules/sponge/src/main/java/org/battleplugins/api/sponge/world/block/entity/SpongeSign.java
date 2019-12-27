package org.battleplugins.api.sponge.world.block.entity;

import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.text.Text;

public class SpongeSign extends SpongeBlockEntity<Sign> implements org.battleplugins.api.world.block.entity.Sign {

    public SpongeSign(Sign sign) {
        super(sign);
    }

    @Override
    public String getLine(int index) {
        return Text.of(handle.getSignData().get(index)).toPlain();
    }

    @Override
    public String[] getLines() {
        String[] lines = new String[handle.getSignData().lines().size()];
        for (int i = 0; i < handle.getSignData().lines().size(); i++) {
            lines[i] = getLine(i);
        }

        return lines;
    }

    @Override
    public void setLine(int index, String line) {
        SignData signData = handle.getOrCreate(SignData.class).get();
        signData.set(signData.lines().set(index, Text.of(line)));
        handle.offer(signData);
    }
}
