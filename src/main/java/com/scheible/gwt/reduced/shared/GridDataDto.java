package com.scheible.gwt.reduced.shared;

import com.scheible.gwt.reduced.shared.framework.collection.Array;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class GridDataDto {

    public Array<ColumnDefinitionDto> columnDefinitions;
    public Array<Array<Object>> rows;

    @JsOverlay
    public static GridDataDto create(Array<ColumnDefinitionDto> columnDefinitions, Array<Array<Object>> rows) {
        GridDataDto gridData = new GridDataDto();
        gridData.columnDefinitions = columnDefinitions;
        gridData.rows = rows;
        return gridData;
    }

    @JsOverlay
    public final Array<ColumnDefinitionDto> getColumnDefinitions() {
        return columnDefinitions;
    }
    
    @JsOverlay
    public final Array<Array<Object>> getRows() {
        return rows;
    }
}
