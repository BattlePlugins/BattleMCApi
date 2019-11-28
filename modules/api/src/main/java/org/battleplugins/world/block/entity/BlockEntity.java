package org.battleplugins.world.block.entity;

import org.battleplugins.world.block.Block;

public interface BlockEntity {

    Block getBlock();

    void update();
    void update(boolean update);
}
