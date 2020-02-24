package org.battleplugins.api;

import lombok.Builder;
import lombok.Getter;

import mc.euro.version.Version;

/**
 * Represents a platform implementation supported
 * by this API.
 */
@Builder
@Getter
public class PlatformType implements Cloneable {

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
	private Version<Platform> minimumVersion;

	PlatformType(String name, Version<Platform> minimumVersion) {
		this.name = name;
		this.minimumVersion = minimumVersion;

		PlatformTypes.platformTypes.add(this);
	}

	/**
	 * Clones the platform type
	 *
	 * @return the cloned platform type
	 */
	@Override
	public PlatformType clone() {
		return new PlatformType(name, minimumVersion);
	}

	/**
	 * Clones the platform type with the
	 * given minimum version
	 *
	 * @param minimumVersion the minimum version
	 * @return the platform type with the given minimum version
	 */
	public PlatformType clone(Version<Platform> minimumVersion) {
		return new PlatformType(name, minimumVersion);
	}
}
