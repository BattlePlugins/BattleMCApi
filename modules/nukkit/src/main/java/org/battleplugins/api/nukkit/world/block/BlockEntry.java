package org.battleplugins.api.nukkit.world.block;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class BlockEntry {

    @SerializedName("bedrock_identifier")
    private String bedrockIdentifier;

    @SerializedName("bedrock_data")
    private int bedrockData;
}
