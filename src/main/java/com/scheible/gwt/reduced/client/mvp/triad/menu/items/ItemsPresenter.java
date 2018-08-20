package com.scheible.gwt.reduced.client.mvp.triad.menu.items;

import com.scheible.gwt.reduced.client.framework.browser.Window;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.ContainerPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import com.scheible.gwt.reduced.client.framework.mvp.ViewWithUiHandlers;
import com.scheible.gwt.reduced.client.mvp.Navigation;
import com.scheible.gwt.reduced.shared.GridDataDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class ItemsPresenter extends ContainerPresenter<ItemsPresenter.MyView, Void> implements ItemsUiHandlers {

    interface MyView extends View {
        
        void initialize(Element container, List<Element> childEls, Void param, String href);
        
        void setGridData(GridDataDto gridData);
        
        void setSelectedItemId(Long selectedItemId);
        
        Long getSelectedItemId();
    }

    private final ItemsModel model;
    private final MyView view;

    ItemsPresenter(String name, Optional<String> urlFragment, ItemsModel model, ItemsPresenter.MyView view, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
        this.model = model;
        this.view = view;
        ((ViewWithUiHandlers) this.view).setUiHandlers(this);
        
        this.model.selectedItemId.addChangeListener((oldValue, value) -> {
            this.view.setSelectedItemId(value);
            if(!((this.view.getSelectedItemId() == null && value == null)
                    || (this.view.getSelectedItemId() != null && value != null && this.view.getSelectedItemId().equals(value)))) {
                Window.getLocation().setHash(Navigation.getItemsHref());
            }
        });
    }
    
    public ItemsPresenter(String name, Optional<String> urlFragment, ItemsModel itemsModel, AbstractPresenter... children) {
        this(name, urlFragment, itemsModel, new ItemsView(), children);
    }

    @Override
    protected void onInitialize(List<Element> children, Void param, String href) {
        view.initialize(getContainer().get(), children, param, href);
    }

    @Override
    protected Promise<?, ?> onActivate(Void param, String href, boolean isTarget) {
        super.onActivate(param, href, isTarget);
        
        Promise<GridDataDto, Void> promise = model.gridDate.get();
        promise.then((GridDataDto gridData) -> {
            view.setGridData(gridData);
            
            if(isTarget && model.selectedItemId.get() != null) {
                Window.getLocation().setHash(Navigation.getSelectedItemHref(model.selectedItemId.get()));
            }
        }, null);
        return promise;        
    }
    
    @Override
    public void onItemSelected(Long selectedItemId) {
        if(selectedItemId != null) {
            Window.getLocation().setHash(Navigation.getSelectedItemHref(selectedItemId));
        } else {
            Window.getLocation().setHash(Navigation.getItemsHref());
        }
    }
}
