package com.scheible.gwt.reduced.shared.framework.collection;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Array")
public interface Array<T> {

    void push(T value);

    @JsProperty(name = "length")
    int getLength();

    @JsOverlay
    default T get(int index) {
        return JsArrayHelper.getArrayValue(this, index);
    }

    @JsOverlay
    public default Stream<T> stream() {
        return StreamSupport.stream(new ArrayIterable<>(this).spliterator(), false);
    }
}
