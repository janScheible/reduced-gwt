package com.scheible.gwt.reduced.client.framework.browser;

import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface Console {
    
    void log(Object... objects);
}
