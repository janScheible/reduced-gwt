package com.scheible.gwt.reduced.shared;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class ColumnDefinitionDto {

    public String columnClassName;
    public String title;

    @JsOverlay
    public static ColumnDefinitionDto create(Class columnClass, String title) {
        ColumnDefinitionDto columnDefinition = new ColumnDefinitionDto();
        columnDefinition.columnClassName = columnClass.getName();
        columnDefinition.title = title;
        return columnDefinition;
    }
}
