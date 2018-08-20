package com.scheible.gwt.reduced.client.framework.navigation;

import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface EnterStateEventCallback<P> {
    
    Promise<?, ?> perform(P param, String href, boolean isTarget);
}
