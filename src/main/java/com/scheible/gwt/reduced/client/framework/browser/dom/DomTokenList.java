package com.scheible.gwt.reduced.client.framework.browser.dom;

import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface DomTokenList {

    void add(String token);

    void remove(String token);
    
    boolean contains(String token);
}
