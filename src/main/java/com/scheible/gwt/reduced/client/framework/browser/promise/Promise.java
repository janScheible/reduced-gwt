package com.scheible.gwt.reduced.client.framework.browser.promise;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Promise")
public class Promise<V, E> {

    @JsConstructor
    public Promise(Executor<V, E> executor) {
        // keep the constructor empty because in reality it is implement in the javascript world
    }

    @JsMethod
    public native Promise<V, E> then(Resolution<V> resolution, Rejection<E> rejection);
}
