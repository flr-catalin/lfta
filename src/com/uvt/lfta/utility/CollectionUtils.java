package com.uvt.lfta.utility;

import java.util.Collection;

/**
 * Utility class for working with collections.
 * 
 * @author Catalin Florea
 */
public class CollectionUtils {

	/**
	 * Checks if the given collection has the given size.
	 * 
	 * @param collection the given collection
	 * @param size the given size
	 * @return true, if the collection size and the given size are the same
	 */
	public static boolean isSize(Collection<?> collection, int size) {
		if (collection == null) {
			return false;
		}
		
		return size(collection) == size;
	}
	
	/**
	 * Gets the size of a collection.
	 * 
	 * @param collection the collection
	 * @return the size of the collection
	 */
	public static int size(Collection<?> collection) {
		if (collection == null) {
			return 0;
		}
		
		return collection.size();
	}
	
}
