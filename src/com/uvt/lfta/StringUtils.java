package com.uvt.lfta;

public class StringUtils {

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
