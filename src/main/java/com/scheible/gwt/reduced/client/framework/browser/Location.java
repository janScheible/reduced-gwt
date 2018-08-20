package com.scheible.gwt.reduced.client.framework.browser;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface Location {
    
    @JsProperty
    String getHash();
    
    @JsProperty
    void setHash(String anchorname);
}
