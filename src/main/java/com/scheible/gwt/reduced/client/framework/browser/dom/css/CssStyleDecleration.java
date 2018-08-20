package com.scheible.gwt.reduced.client.framework.browser.dom.css;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface CssStyleDecleration {
	
	@JsMethod
	void setProperty(String propertyName, String value);
}
