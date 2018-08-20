package com.scheible.gwt.reduced.client.framework.browser;

import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public class Json {

    public native String stringify(Object o);

    public native <T> T parse(String json);
}
