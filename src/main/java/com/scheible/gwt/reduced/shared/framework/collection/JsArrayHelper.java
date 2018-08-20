package com.scheible.gwt.reduced.shared.framework.collection;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
public class JsArrayHelper {

    @JsFunction
    @FunctionalInterface
    public interface CreateArrayMethod<T> {

        Array<T> execute();
    }

    @JsFunction
    @FunctionalInterface
    public interface GetArrayValueMethod<T> {

        T execute(Array<T> a, int i);
    }

    private static CreateArrayMethod createArrayMethod;
    private static GetArrayValueMethod getArrayValueMethod;

    public static void setMethods(CreateArrayMethod createArrayMethod, GetArrayValueMethod getArrayValueMethod) {
        JsArrayHelper.createArrayMethod = createArrayMethod;
        JsArrayHelper.getArrayValueMethod = getArrayValueMethod;
    }

    public static <T extends Object> Array<T> createArray() {
        return createArrayMethod.execute();
    }

    public static <T> T getArrayValue(Array<T> a, int i) {
        return (T) getArrayValueMethod.execute(a, i);
    }
}
