package org.battleplugins.api.nukkit.registry.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
class ItemEntry {

    @SerializedName("bedrock_id")
    private int bedrockId;

    @SerializedName("bedrock_data")
    private int bedrockData;
}
