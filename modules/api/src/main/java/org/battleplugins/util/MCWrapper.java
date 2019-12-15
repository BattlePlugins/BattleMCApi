package org.battleplugins.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Wraps an implementation class.
 *
 * @param <T> the platform implementation
 */
@AllArgsConstructor
@Data
public class MCWrapper<T> {

    /**
     * The platform implementation handle
     *
     * @return the platform implementation handle
     */
    @Setter(AccessLevel.NONE)
    protected T handle;
}
