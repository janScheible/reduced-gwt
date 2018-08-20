package com.scheible.gwt.reduced.client.element.grid;

import com.scheible.gwt.reduced.client.framework.application.GwtApplication;
import jsinterop.annotations.JsType;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;

/**
 * @param <T> Either {@code String} or {@code Double} (in case of {@code Double} {@code IntegerRowSelectionAdapter} or 
 *            {@code LongRowSelectionAdapter} can be used).
 * 
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
abstract class GridElement<T> implements NativeGrid, Element {

    @JsOverlay
    static <T> GridElement<T> create(String id) {
        GridElement customElement = document().createElement(GwtApplication.getId() + "-grid");
        customElement.setAttribute("id", id);
        return customElement;
    }

    @JsOverlay
    static GridElement findById(String id) {
        return (GridElement) (document().getElementById(id));
    }
}
