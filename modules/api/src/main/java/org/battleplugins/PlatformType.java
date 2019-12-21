package org.battleplugins;

import lombok.Builder;
import lombok.Getter;

import mc.euro.version.Version;

/**
 * Represents a platform implementation supported
 * by this API.
 */
@Builder
@Getter
public class PlatformType {

	/**
	 * The name of the platform type
	 *
	 * @return the name of the platform type
	 */
	private String name;

	/**
	 * The minimum supported {@link Version} of
	 * this platform type
	 *
	 * @return the minimum supported version
	 */
	private Version minimumVersion;

	PlatformType(String name, Version minimumVersion) {
		this.name = name;
		this.minimumVersion = minimumVersion;

		PlatformTypes.platformTypes.add(this);
	}
}
