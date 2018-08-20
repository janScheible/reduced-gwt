package com.scheible.gwt.reduced.client.framework.browser;

import com.scheible.gwt.reduced.client.framework.browser.dom.Document;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "window")
public class Window {
    
    @JsProperty(name = "document")
    public static native Document document();
    
    @JsProperty
    public static native Console getConsole();
    
    @JsProperty(name = "JSON")
    public static native Json json();
    
    @JsProperty
    public static native Location getLocation();
    
    public static native void alert(String message);
    
    @JsProperty
    public static native int getInnerWidth();
    
    @JsProperty
    public static native int getInnerHeight();
}
