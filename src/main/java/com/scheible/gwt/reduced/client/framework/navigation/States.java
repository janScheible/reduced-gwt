package com.scheible.gwt.reduced.client.framework.navigation;

import com.scheible.gwt.reduced.shared.framework.collection.Array;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class States {

    @JsProperty
    public native void setStates(Array<State> states);

    @JsProperty
    public native Array<State> getStates();

    @JsProperty
    public native void setDefaultState(State defaultState);

    @JsProperty
    public native State getDefaultState();
}
