package com.scheible.gwt.reduced.client.mvp.triad.home;

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
public class HomePresenter extends ContainerPresenter<HomePresenter.MyView, Void> implements HomeUiHandlers {

    interface MyView extends View {
        
        void initialize(Element container, List<Element> childEls, Void param, String href);
    }
    
    private final HomePresenter.MyView view;
    
    HomePresenter(String name, Optional<String> urlFragment, HomePresenter.MyView view, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
        this.view = view;
        ((ViewWithUiHandlers)this.view).setUiHandlers(this);
    }
    
    public HomePresenter(String name, Optional<String> urlFragment, AbstractPresenter... children) {
        this(name, urlFragment, new HomeView(), children);
    }

    @Override
    public void onInitialize(List<Element> children, Void param, String href) {
        view.initialize(getContainer().get(), children, param, href);
    }

    @Override
    public Promise<?, ?> onActivate(Void param, String href, boolean isTarget) {
        return super.onActivate(param, href, isTarget);
    }

    @Override
    public void onDeactivate(Void param, String href, boolean isSubTreeChange) {
        super.onDeactivate(param, href, isSubTreeChange);
    }
    
    @Override
    public void onItemsSelected() {
    }

    @Override
    public void onOthersSelected() {
    }    
}
