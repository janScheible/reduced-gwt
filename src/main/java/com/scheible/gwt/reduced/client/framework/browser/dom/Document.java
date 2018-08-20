package com.scheible.gwt.reduced.client.framework.browser.dom;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface Document {
    
    @JsProperty
    Element getHead();
    
    @JsProperty
    Element getBody();    
    
    <T> T createElement(String tag);
	
	Node createTextNode(String text);
    
    Element getElementById(String id);
    
    Element querySelector(String selector);
}
