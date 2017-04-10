package com.eretailservice.model;

import java.util.List;

/**
 * the store interface
 * 
 * @author yinchun
 *
 * @param <T>
 *            the object to rooms
 * @param <M>
 *            the searching criteria
 */
public interface ASubPlace<T, M> {

	/**
	 * retrieve an item
	 * 
	 * @return
	 */
	public T get(M searchCriteria);

	/**
	 * retrieve all the items
	 * 
	 * @return
	 */
	default public List<T> getAll() {
		return null;
	}

	/**
	 * add an item
	 * 
	 * @param item
	 */
	public T add(T item);
}
