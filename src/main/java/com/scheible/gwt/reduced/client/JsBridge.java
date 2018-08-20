package com.scheible.gwt.reduced.client;

import com.scheible.gwt.reduced.client.framework.application.FrameworkMethods;
import jsinterop.annotations.JsType;

/**
 * The package name of that class is used to move the JavaScript for the application to the correct namespace.
 * It is also neither allowed to rename the class or the <code>init()</code> method cause then JavaScript side
 * would not be able to find it.
 * 
 * @author sj
 */
@JsType(isNative = true)
public class JsBridge {
    
    public static native FrameworkMethods init(String applicationId);
}
