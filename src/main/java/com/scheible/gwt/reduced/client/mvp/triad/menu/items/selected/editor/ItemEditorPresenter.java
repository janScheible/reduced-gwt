package com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.editor;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import com.scheible.gwt.reduced.client.framework.fluent.Fluent;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.DialogPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class ItemEditorPresenter extends DialogPresenter<View, Void> {

    private final ItemEditorModel model;
    
    private Fluent closeButton;
    private Fluent idEl;

    public ItemEditorPresenter(String name, Optional<String> urlFragment, ItemEditorModel model, AbstractPresenter... children) {
        super(name, urlFragment, children);
        this.model = model;
    }

    @Override
    protected void onInitialize(List<Element> children, Void param, String href) {
        super.onInitialize(children, param, href);
        getDialog().setButtonBar((
            closeButton = $("<a>").attr("href", getCloseHref()).append(
                $("<button>").attr("type", "button").text("Close"))
        ).element());
        getDialog().setContent((
            $("<div>").append(
                $("<span>").text("Id: "),
                idEl = $("<span>")
            )
        ).element());
    }

    @Override
    protected Promise<?, ?> onActivate(Void param, String href, boolean isTarget) {
        super.onActivate(param, href, isTarget);
        closeButton.attr("href", getCloseHref());
        idEl.text(model.selectedItemId.get() != null ? Long.toString(model.selectedItemId.get()) : "");
        return null;
    }
}
