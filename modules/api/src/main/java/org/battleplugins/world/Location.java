package org.battleplugins.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a location in the world.
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Location {

	/**
	 * The world the location is in.
	 *
	 * @return the world the location is in
	 *
	 */
	private final World world;

	/**
	 * The x coordinate of the location
	 *
	 * @return the x coordinate of the location
	 */
	private final double x;

	/**
	 * The y coordinate of the location
	 *
	 * @return the y coordinate of the location
	 */
	private final double y;

	/**
	 * The z coordinate of the location
	 *
	 * @return the z coordinate of the location
	 */
	private final double z;

	/**
	 * The pitch of the location
	 *
	 * @return the pitch of the location
	 */
	private float pitch;

	/**
	 * The yaw of the location
	 *
	 * @return the yaw of the location
	 */
	private float yaw;

	/**
	 * The block x coordinate of the location
	 *
	 * @return the block x coordinate of the location
	 */
	public int getBlockX() {
		return (int) Math.floor(x);
	}

	/**
	 * The block y coordinate of the location
	 *
	 * @return the block y coordinate of the location
	 */
	public int getBlockY() {
		return (int) Math.floor(y);
	}

	/**
	 * The block z coordinate of the location
	 *
	 * @return the block z coordinate of the location
	 */
	public int getBlockZ() {
		return (int) Math.floor(z);
	}
}
