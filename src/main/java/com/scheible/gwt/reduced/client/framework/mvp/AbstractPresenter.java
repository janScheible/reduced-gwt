package com.scheible.gwt.reduced.client.framework.mvp;

import static com.scheible.gwt.reduced.client.framework.browser.Window.getConsole;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import com.scheible.gwt.reduced.client.framework.fluent.Fluent;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public abstract class AbstractPresenter<V extends View, P>  {

    private final String name;
    private final Optional<String> urlFragment;
    private final List<AbstractPresenter> children;
    
    private Optional<Element> container;
    private boolean initialized = false;
    
    private boolean parentDefaultView = false;

    public AbstractPresenter(String name, Optional<String> urlFragment, Optional<Element> container, AbstractPresenter... children) {
        this.name = name;
        this.urlFragment = urlFragment;
        this.children = Arrays.asList(children);
        this.container = container;
    }

    protected void setContainer(Optional<Element> container) {
        this.container = container;
    }
    
    public Promise<?, ?> onEnter(P param, String href, boolean isTarget) {
        String action = "activated";
        if (!initialized) {
            action = "initialized + " + action;
            initialized = true;
            onInitialize(prepareChildPresenters(), param, href);
        }
        Promise<?, ?> promise = onActivate(param, href, isTarget);
        logStateEvent(action, param, href, "isTarget", isTarget);
        return promise;
    }
    
    public void onExit(P param, String href, boolean isSubTreeChange) {
        onDeactivate(param, href, isSubTreeChange);
        logStateEvent("deactivated", param, href, "isSubTreeChange", isSubTreeChange);
    }
    
    private void logStateEvent(String action, P param, String href, String boolName, boolean boolValue) {
        getConsole().log("c.s.AbstractPresenter [" + action + "] " + name + " (param: '" + param 
                + "', href: '" + href + ", " + boolName + ": " + boolValue + ")");
    }
    
    protected List<Element> prepareChildPresenters() {
        return new ArrayList();
    }
    
    protected final void enableParentDefaultView() {
        parentDefaultView = true;
    }
    
    protected void onInitialize(List<Element> children, P param, String href) {        
    }
    
    protected Promise<?, ?> onActivate(P param, String href, boolean isTarget) {
        container.ifPresent(element -> {
            Fluent el = $(element).show();
            
            if(hasParentDefaultView()) {
                el.parent().findFirst("#" + el.id() + "-" + View.PARENT_DEFAULT_VIEW_CSS_CLASS).get().hide();
            }
        });
        return null;
    }
    
    protected void onDeactivate(P param, String href, boolean isSubTreeChange) {
        container.ifPresent(element -> {
            Fluent el = $(element).hide();
            
            if(hasParentDefaultView()) {
                el.parent().findFirst("#" + el.id() + "-" + View.PARENT_DEFAULT_VIEW_CSS_CLASS).get().show();
            }
        });
    }

    public List<AbstractPresenter> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getUrlFragment() {
        return urlFragment;
    }

    protected Optional<Element> getContainer() {
        return container;
    }
    
    protected boolean hasParentDefaultView() {
        return parentDefaultView;
    }
}
