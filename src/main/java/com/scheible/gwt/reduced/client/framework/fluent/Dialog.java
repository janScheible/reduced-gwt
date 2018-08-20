package com.scheible.gwt.reduced.client.framework.fluent;

import com.scheible.gwt.reduced.client.framework.browser.Window;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;

/**
 *
 * @author sj
 */
public class Dialog implements ElementWrapper {
    
    private final Element outer;
    
    private final Fluent content;
    private final Fluent buttonBar;

    private Dialog(Element outer, Fluent content, Fluent buttonBar) {
        this.outer = outer;
        
        this.content = content;
        this.buttonBar = buttonBar;
    }
    
    public static Dialog createModal(String id, String title, int width, int height) {
        int xOffset = (Window.getInnerWidth() - width) / 2;
        int yOffset = (Window.getInnerHeight() - height) / 2;
        
        Fluent buttonBar, content;
        Element outer = $("<div>").attr("id", id + "-fade").css("position", "absolute")
                .css("left", "0px").css("right", "0px").css("top", "0px").css("bottom", "0px")
                .css("background-color", "rgba(0,0,0,0.5)").append(
            $("<div>").attr("id", id + "-window").css("position", "absolute")
                    .css("left", xOffset + "px").css("right", xOffset + "px").css("top", yOffset + "px").css("height", height + "px")
                    .css("background-color", "gainsboro").css("display", "flex").css("flex-direction", "column").append(
                $("<div>").text(title).css("background-color", "gold"),
                content = $("<div>").css("flex-grow", "1"),
                buttonBar = $("<div>").css("text-align", "right")
            )
        ).hide().element();
        
        return new Dialog(outer, content, buttonBar);
    }

    public Fluent getContent() {
        return content;
    }

    public Fluent getButtonBar() {
        return buttonBar;
    }

    @Override
    public Element element() {
        return outer;
    }
}
