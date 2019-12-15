package org.battleplugins.world;

/**
 * Represents a location in the world.
 */
public interface Location {

	/**
	 * The world the location is in.
	 *
	 * @return the world the location is in
	 *
	 */
	World getWorld();

	/**
	 * The x coordinate of the location
	 *
	 * @return the x coordinate of the location
	 */
	double getX();

	/**
	 * The y coordinate of the location
	 *
	 * @return the y coordinate of the location
	 */
	double getY();

	/**
	 * The z coordinate of the location
	 *
	 * @return the z coordinate of the location
	 */
	double getZ();

	/**
	 * The pitch of the location
	 *
	 * @return the pitch of the location
	 */
	float getPitch();

	/**
	 * The yaw of the location
	 *
	 * @return the yaw of the location
	 */
	float getYaw();

	/**
	 * The block x coordinate of the location
	 *
	 * @return the block x coordinate of the location
	 */
	default int getBlockX() {
		return (int) Math.floor(getX());
	}

	/**
	 * The block y coordinate of the location
	 *
	 * @return the block y coordinate of the location
	 */
	default int getBlockY() {
		return (int) Math.floor(getY());
	}

	/**
	 * The block z coordinate of the location
	 *
	 * @return the block z coordinate of the location
	 */
	default int getBlockZ() {
		return (int) Math.floor(getZ());
	}
}
