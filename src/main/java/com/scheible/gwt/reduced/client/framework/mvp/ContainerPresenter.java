package com.scheible.gwt.reduced.client.framework.mvp;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author sj
 */
public abstract class ContainerPresenter<V extends View, P> extends AbstractPresenter<V, P> {
    
    public ContainerPresenter(String name, Optional<String> urlFragment, Optional<Element> container, AbstractPresenter... children) {
        super(name, urlFragment, container, children);
    }

    @Override
    protected List<Element> prepareChildPresenters() {
        return getChildren().stream().filter(child -> child instanceof ContainerPresenter)
                .map(child -> {
                    Element childElement = $("<div>").addClass("container").hide()
                            .id(ViewId.getUniqueId(child.getName() + "-container"))
                            .addClass(child::hasParentDefaultView, View.PARENT_DEFAULT_VIEW_CSS_CLASS)
                            .element();
                    
                    child.setContainer(Optional.of(childElement));
                    return childElement;
                }).collect(Collectors.toList());
    }       
}
