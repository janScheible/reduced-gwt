package com.scheible.gwt.reduced.client.element.grid;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface CellRenderer<T> {
    
    String render(T value);
}
