package com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.fluent.Fluent;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.ViewWithUiHandlers;
import com.scheible.gwt.reduced.client.mvp.Navigation;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class SelectedItemView extends ViewWithUiHandlers<SelectedItemUiHandlers> implements SelectedItemPresenter.MyView {

    private Fluent idEl;
    private Fluent editLinkEl;

    @Override
    public void initialize(Element container, List<Element> childEls, Long param, String href) {
        $(container).append(
            $("<div>").append(
                $("<span>").text("Id: "),
                idEl = $("<span>")
            ),
            editLinkEl = $("<a>").text("Edit").css("pointer-events", "none").css("cursor", "default")
        );
    }    

    @Override
    public void setId(Optional<Long> itemId) {
        idEl.text(itemId.isPresent() ? itemId.get().toString() : "-");
        
        if(itemId.isPresent()) {
            editLinkEl.css("pointer-events", "auto").css("cursor", "auto")
                    .attr("href", Navigation.getItemEditorHref(itemId.get()));            
        } else {
            editLinkEl.css("pointer-events", "none").css("cursor", "default")
                    .attr("href", "");
        }
    }
}
