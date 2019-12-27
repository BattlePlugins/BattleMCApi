package org.battleplugins.api.inventory.item.component.flag;

import lombok.Getter;

@Getter
public class ItemFlag {

    private String name;

    ItemFlag(String name) {
        this.name = name;
    }
}
