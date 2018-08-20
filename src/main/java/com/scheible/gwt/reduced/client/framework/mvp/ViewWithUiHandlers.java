package com.scheible.gwt.reduced.client.framework.mvp;

/**
 *
 * @author sj
 */
public abstract class ViewWithUiHandlers<T extends UiHandlers> implements View {

    private T uiHandlers;

    public void setUiHandlers(T uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    protected T getUiHandlers() {
        return uiHandlers;
    }
}
