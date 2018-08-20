package com.scheible.gwt.reduced.client.element.grid;

import com.scheible.gwt.reduced.shared.framework.collection.Array;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface NativeGrid<T> {
    
    @JsProperty
    void setRenderers(Array<CellRenderer> renderers);

    void setRows(Array<String> titles, Array<Array<Object>> rows);
    
    void addRowSelectionListener(RowSelectionListener<T> listener);

    @JsProperty
    void setSelected(T id);
    
    @JsProperty
    T getSelected();    
}
