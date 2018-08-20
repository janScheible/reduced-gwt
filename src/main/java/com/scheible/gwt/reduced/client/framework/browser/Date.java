package com.scheible.gwt.reduced.client.framework.browser;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Date {

    public Date(String iso8601DateTime) {
    }

    public native String toLocaleString();
}
