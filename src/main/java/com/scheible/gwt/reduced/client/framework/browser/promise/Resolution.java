package com.scheible.gwt.reduced.client.framework.browser.promise;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface Resolution<T> {

    void resolved(T value);
}
