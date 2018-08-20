package com.scheible.gwt.reduced.client.framework.browser.request;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "XMLHttpRequest")
public class XMLHttpRequest {

    @JsMethod
    public native void open(String method, String url, boolean isAsync);

    @JsMethod
    public native void send(Object data);

    @JsProperty(name = "onreadystatechange")
    public native void setOnReadyStateChange(ReadyStateHandler handler);

    @JsMethod
    public native void setRequestHeader(String headerName, String value);

    @JsProperty
    public native int getReadyState();

    @JsProperty
    public native int getStatus();
    
    @JsProperty
    public native String getStatusText();    

    @JsProperty
    public native String getResponseText();
}
