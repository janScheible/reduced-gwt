package com.scheible.gwt.reduced.client.framework.browser.promise;

import jsinterop.annotations.JsFunction;

/**
 *
 * @author sj
 */
@FunctionalInterface
@JsFunction
public interface Executor<V, E> {

    void execute(Resolver<V> resolver, Rejector<E> rejecter);
}
