package com.scheible.gwt.reduced.shared;

import com.scheible.gwt.reduced.shared.framework.dto.Typeable;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
@Typeable
public class Iso8601DateTimeDto {

    public String value;

    @JsOverlay
    public static String getClassName() {
        return Iso8601DateTimeDtoMeta.CLASS_NAME;
    }
    
    @JsOverlay
    public static Iso8601DateTimeDto create(String value) {
        Iso8601DateTimeDto iso8601DateTime = new Iso8601DateTimeDto();
        iso8601DateTime.value = value;
        return iso8601DateTime;
    }    
}
