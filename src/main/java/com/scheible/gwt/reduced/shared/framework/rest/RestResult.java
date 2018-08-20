package com.scheible.gwt.reduced.shared.framework.rest;

/**
 *
 * @author sj
 */
public interface RestResult<T> {
	
	void then(RestCallback<T> callback);
}
