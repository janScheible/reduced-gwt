package com.scheible.gwt.reduced.shared.framework.rest;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@JsFunction
@FunctionalInterface
public interface RestCallback<T> {
	
	void on(Integer httpStatusCode, T body);
}
