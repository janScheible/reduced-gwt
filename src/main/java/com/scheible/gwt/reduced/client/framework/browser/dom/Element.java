package com.scheible.gwt.reduced.client.framework.browser.dom;

import com.scheible.gwt.reduced.client.framework.browser.dom.css.CssStyleDecleration;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface Element extends Node {

    void setAttribute(String name, Object value);

    String getAttribute(String name);
	
	@JsProperty
	CssStyleDecleration getStyle();
    
    @JsProperty
    DomTokenList getClassList();
    
    Element querySelector(String selector);
}
