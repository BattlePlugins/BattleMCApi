package org.battleplugins.nukkit.inventory.item;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
public class ItemEntry {

    @SerializedName("bedrock_id")
    private int bedrockId;

    @SerializedName("bedrock_data")
    private int bedrockData;
}
