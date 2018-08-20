package com.scheible.gwt.reduced.client.element.grid;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface RowSelectionListener<T> {
    
    void onSelect(T id);
}
