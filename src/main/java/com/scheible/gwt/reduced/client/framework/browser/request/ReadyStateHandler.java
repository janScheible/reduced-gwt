package com.scheible.gwt.reduced.client.framework.browser.request;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@JsFunction
@FunctionalInterface
public interface ReadyStateHandler {

    void onStateChanged(Object event);
}
