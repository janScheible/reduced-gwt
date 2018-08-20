package com.scheible.gwt.reduced.client.framework.browser.dom;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true)
public interface Node {

    @JsProperty
    Node getParentNode();    
    
    @JsProperty
    void setTextContent(String textContent);
    
    @JsProperty
    String getTextContent();    

    void appendChild(Object child);
    
    @JsProperty
    Node getFirstChild();

    Node insertBefore(Node newChild, Node refChild);
}
