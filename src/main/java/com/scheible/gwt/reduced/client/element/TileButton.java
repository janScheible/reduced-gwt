package com.scheible.gwt.reduced.client.element;

import com.scheible.gwt.reduced.client.framework.application.GwtApplication;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public abstract class TileButton implements Element {
    
    public static class Builder {

        private TileButton tileButton;

        public Builder(String id, String text, String href) {
            tileButton = TileButton.create(id);
            tileButton.setText(text);
            tileButton.setHref(href);
        }

        public Builder setBackgroundColor(String color) {
            tileButton.setBackgroundColor(color);
            return this;
        }

        public Builder setFontColor(String color) {
            tileButton.setFontColor(color);
            return this;
        }

        public TileButton build() {
            return tileButton;
        }
    }

    @JsOverlay
    public static TileButton create(String id) {
        TileButton customElement = document().createElement(GwtApplication.getId() + "-tile-button");
        customElement.setAttribute("id", id);
        return customElement;
    }

    @JsOverlay
    public static TileButton findById(String id) {
        return (TileButton) (document().getElementById(id));
    }
    
    @JsProperty
    public native void setText(String text);
    
    @JsProperty
    public native void setHref(String href);    
    
    @JsProperty
    public native void setBackgroundColor(String color);

    @JsProperty
    public native void setFontColor(String color);
}
