package com.scheible.gwt.reduced.client.framework.application;

import com.scheible.gwt.reduced.client.framework.navigation.UiRouter;
import com.scheible.gwt.reduced.shared.framework.collection.JsArrayHelper;
import java.util.function.Function;

/**
 *
 * @author sj
 */
public class GwtApplication {
    
    private static String id;

    public static void init(Function<String, FrameworkMethods> initializer, String id) {
        GwtApplication.id = id;
        FrameworkMethods frameworkMethods = initializer.apply(id);
        
        UiRouter.setMethods(frameworkMethods.getUiRouterInitializeMethod(), 
                frameworkMethods.getUiRouterGetHrefMethod());
        JsArrayHelper.setMethods(frameworkMethods.getJsArrayHelperCreateArrayMethod(), 
                frameworkMethods.getJsArrayHelperGetArrayValueMethod());
    }

    public static String getId() {
        return id;
    }
}
