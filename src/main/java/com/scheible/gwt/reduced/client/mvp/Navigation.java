package com.scheible.gwt.reduced.client.mvp;

import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.navigation.UiRouter;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
public class Navigation {
    
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    private static class SelectedItemParams {
        
        @JsProperty
        private native void setItemId(double itemId);
    }

    private static String homePresenterName;
    private static String itemsPresenterName;
    private static String selectedItemPresenterName;
    private static String itemEditorPresenterName;
    private static String othersPresenterName;

    public static void initialize(AbstractPresenter<?, ?> homePresenter, AbstractPresenter<?, ?> itemsPresenter,
            AbstractPresenter<?, ?> selectedItemPresenter, AbstractPresenter<?, ?> itemEditorPresenter,
            AbstractPresenter<?, ?> othersPresenter) {
        Navigation.homePresenterName = homePresenter.getName();
        Navigation.itemsPresenterName = itemsPresenter.getName();
        Navigation.selectedItemPresenterName = selectedItemPresenter.getName();
        Navigation.itemEditorPresenterName = itemEditorPresenter.getName();
        Navigation.othersPresenterName = othersPresenter.getName();
    }

    public static String getHomeHref() {
        return UiRouter.getHref(homePresenterName, null);
    }

    public static String getItemsHref() {
        return UiRouter.getHref(itemsPresenterName, null);
    }

    public static String getOthersHref() {
        return UiRouter.getHref(othersPresenterName, null);
    }
    
    public static String getSelectedItemHref(long itemId) {
        SelectedItemParams params = new SelectedItemParams();
        params.setItemId(Long.valueOf(itemId).doubleValue());
        return UiRouter.getHref(selectedItemPresenterName, params);
    } 
    
    public static String getItemEditorHref(long itemId) {
        SelectedItemParams params = new SelectedItemParams();
        params.setItemId(Long.valueOf(itemId).doubleValue());
        return UiRouter.getHref(itemEditorPresenterName, params);
    }     
}
