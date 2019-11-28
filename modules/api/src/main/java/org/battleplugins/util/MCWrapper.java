package org.battleplugins.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
public class MCWrapper<T> {

    @Setter(AccessLevel.NONE)
    protected T handle;
}
