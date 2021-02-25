package com.uvt.lfta;

/**
 * Utility class for working with strings.
 * 
 * @author Catalin Florea
 *
 */
public class StringUtils {

	/**
	 * Null safe isEqual() implementation.
	 * 
	 * @param source the source
	 * @param destination the destination
	 * @return true, if the strings are equal or both null
	 */
	public static boolean isEqual(String source, String destination) {
		if (source == null && destination == null) {
			return true;
		}
		
		if (source == null || destination == null) {
			return false;
		}
		
		return source.equals(destination);
	}
	
}
