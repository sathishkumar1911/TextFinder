package com.cl.sample;

/**
 * @author dharmaraj
 *
 */
public class Utils {

	/**
	 * Method to check the string is empty or not.
	 * Returns true if the string is empty or contains only spaces else returns false
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isEmptyStringWithoutSpace(String value) {
		if (value == null || value.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check the string is empty or not
	 * Returns true if the string is empty else returns false
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isEmptyString(String value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}
}
