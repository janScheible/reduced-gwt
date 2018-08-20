package com.scheible.gwt.reduced.client.mvp.triad;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.ContainerPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class AppPresenter extends ContainerPresenter<View, Void> {

    public AppPresenter(String name, Optional<String> urlFragment, Element container, AbstractPresenter... children) {
        super(name, urlFragment, Optional.of(container), children);
    }

    @Override
    protected void onInitialize(List<Element> children, Void param, String href) {
        $(getContainer().get()).css("display", "flex");
        children.forEach(childEl -> $(childEl).css("flex-grow", "1"));
        $(getContainer().get()).append(children);
    }
}
