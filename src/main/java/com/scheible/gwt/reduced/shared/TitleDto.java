package com.scheible.gwt.reduced.shared;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TitleDto {
    
    public String value;
    public Iso8601DateTimeDto dateTime;

    @JsOverlay
    public static TitleDto create(String value, Iso8601DateTimeDto dateTime) {
        TitleDto title = new TitleDto();
        title.value = value;
        title.dateTime = dateTime;
        return title;
    }
    
    @JsOverlay
    public final String getValue() {
        return value;
    }

    @JsOverlay
    public final Iso8601DateTimeDto getDateTime() {
        return dateTime;
    }
}
