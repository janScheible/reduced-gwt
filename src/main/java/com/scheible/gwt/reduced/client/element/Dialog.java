package com.scheible.gwt.reduced.client.element;

import com.scheible.gwt.reduced.client.framework.application.GwtApplication;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public abstract class Dialog implements Element {
    
    public static class Builder {

        private Dialog dialog;

        public Builder(String id, int width, int height) {
            dialog = Dialog.create(id);
            dialog.setWidth(width);
            dialog.setHeight(height);
        }
		
		public Builder setTitle(String title) {
			Element titleSpan = document().createElement("span");
			titleSpan.setTextContent(title);
			titleSpan.setAttribute("slot", "title");
			dialog.appendChild(titleSpan);
			return this;
		}
		
		public Builder setContent(Element element) {
			return this;
		}

		public Builder setButtonBar(Element element) {
			return this;
		}		
		
        public Dialog build() {
            return dialog;
        }
    }

    @JsOverlay
    public static Dialog create(String id) {
        Dialog customElement = document().createElement(GwtApplication.getId() + "-dialog");
        customElement.setAttribute("id", id);
        return customElement;
    }

    @JsOverlay
    public static Dialog findById(String id) {
        return (Dialog) (document().getElementById(id));
    }
    
    @JsProperty
    public native void setWidth(int width);
    
    @JsProperty
    public native void setHeight(int height);
	
	@JsMethod
	public native void show();
	
	@JsMethod
	public native void hide();
    
	@JsMethod
	public native void setContent(Element content);
    
	@JsMethod
	public native void setButtonBar(Element content);    
}
