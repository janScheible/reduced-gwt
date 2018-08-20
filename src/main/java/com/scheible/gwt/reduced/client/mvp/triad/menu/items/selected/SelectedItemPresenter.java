package com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import com.scheible.gwt.reduced.client.framework.mvp.ContainerPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import com.scheible.gwt.reduced.client.framework.mvp.ViewWithUiHandlers;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class SelectedItemPresenter extends ContainerPresenter<SelectedItemPresenter.MyView, /* TODO Long wanted! ;-) */ Double> implements SelectedItemUiHandlers {

    interface MyView extends View {
        
        void initialize(Element container, List<Element> childEls, Long param, String href);
        
        void setId(Optional<Long> itemId);
    }

    private final SelectedItemModel model;
    private MyView view;
    
    SelectedItemPresenter(String name, Optional<String> urlFragment, SelectedItemModel model, SelectedItemPresenter.MyView view, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
        this.model = model;
        this.view = view;
        ((ViewWithUiHandlers) this.view).setUiHandlers(this);
        enableParentDefaultView();
    }
    
    public SelectedItemPresenter(String name, Optional<String> urlFragment, SelectedItemModel model, AbstractPresenter... children) {
        this(name, urlFragment, model, new SelectedItemView(), children);
    }    

    @Override
    protected void onInitialize(List<Element> children, Double param, String href) {
        view.initialize(getContainer().get(), children, param != null ? param.longValue() : null, href);
    }

    @Override
    protected Promise<?, ?> onActivate(Double param, String href, boolean isTarget) {
        super.onActivate(param, href, isTarget);
        model.selectedItemId.set(param.longValue());
        view.setId(Optional.ofNullable(param != null ? param.longValue() : null));
        return null;
    }

    @Override
    protected void onDeactivate(Double param, String href, boolean isSubTreeChange) {
        super.onDeactivate(param, href, isSubTreeChange);
        if(!isSubTreeChange) {
            model.selectedItemId.set(param != null ? param.longValue() : null);
        }
    }
}
