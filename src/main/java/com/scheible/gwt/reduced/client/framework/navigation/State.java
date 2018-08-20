package com.scheible.gwt.reduced.client.framework.navigation;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author js
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class State {

    @JsProperty
    public native void setName(String name);

    @JsProperty
    public native void setParent(String parentName);

    @JsProperty
    public native void setUrl(String url);
    
    @JsProperty
    public native void setParamName(String paramName);

    @JsProperty
    public native void setOnEnterCallback(EnterStateEventCallback callback);

    @JsProperty
    public native void setOnExitCallback(ExitStateEventCallback callback);
}
