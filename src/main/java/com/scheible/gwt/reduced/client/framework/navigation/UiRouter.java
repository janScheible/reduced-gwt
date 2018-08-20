package com.scheible.gwt.reduced.client.framework.navigation;

import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
public class UiRouter {
    
    @JsFunction
    @FunctionalInterface
    public interface InitializeMethod {
        
        void execute(States states);
    }
    
    @JsFunction
    @FunctionalInterface
    public interface GetHrefMethod {
        
        String execute(String stateName, Object params);
    }    
    
    private static InitializeMethod initializeMethod;
    private static GetHrefMethod getHrefMethod;

    public static void setMethods(InitializeMethod initializeMethod, GetHrefMethod getHrefMethod) {
        UiRouter.initializeMethod = initializeMethod;
        UiRouter.getHrefMethod = getHrefMethod;
    }
    
    public static void initialize(States states) {
        initializeMethod.execute(states);
    }
    
    public static String getHref(AbstractPresenter<?, ?> abstractPresenter, Object params) {
        return getHref(abstractPresenter.getName(), params);
    }    
    
    public static String getHref(String stateName, Object params) {
        return getHrefMethod.execute(stateName, params);
    }
}
