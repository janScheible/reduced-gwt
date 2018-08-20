package com.scheible.gwt.reduced.client.framework.navigation;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface ExitStateEventCallback<P> {
    
    void perform(P param, String href, boolean isSubTreeChange);
}
