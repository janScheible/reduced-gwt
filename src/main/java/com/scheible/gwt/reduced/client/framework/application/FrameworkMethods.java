package com.scheible.gwt.reduced.client.framework.application;

import com.scheible.gwt.reduced.client.framework.navigation.UiRouter;
import com.scheible.gwt.reduced.shared.framework.collection.JsArrayHelper;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class FrameworkMethods {
    
    @JsProperty
    public native UiRouter.InitializeMethod getUiRouterInitializeMethod();
    
    @JsProperty
    public native UiRouter.GetHrefMethod getUiRouterGetHrefMethod();    
    
    @JsProperty
    public native JsArrayHelper.CreateArrayMethod getJsArrayHelperCreateArrayMethod();

    @JsProperty
    public native JsArrayHelper.GetArrayValueMethod getJsArrayHelperGetArrayValueMethod();
}
