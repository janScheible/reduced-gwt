package com.scheible.gwt.reduced.client.framework.mvp;

import com.scheible.gwt.reduced.client.element.Dialog;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import static com.scheible.gwt.reduced.client.framework.browser.Window.getLocation;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public abstract class DialogPresenter<V extends View, P> extends AbstractPresenter<V, P> {

    private Dialog dialog;

    public DialogPresenter(String name, Optional<String> urlFragment, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
    }

    @Override
    protected void onInitialize(List<Element> children, P param, String href) {
        dialog = new Dialog.Builder("-dialog", 480, 320).setTitle("Item Editor").build();
        document().getBody().appendChild(dialog);
        dialog.show();
        setContainer(Optional.of(dialog));
    }
    
    protected String getCloseHref() {
        return getLocation().getHash().substring(0, getLocation().getHash().lastIndexOf('/'));
    }

    protected Dialog getDialog() {
        return dialog;
    }
}
