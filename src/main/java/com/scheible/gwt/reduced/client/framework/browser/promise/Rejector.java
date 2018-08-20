package com.scheible.gwt.reduced.client.framework.browser.promise;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface Rejector<T> {
    
	void reject( T error );
}