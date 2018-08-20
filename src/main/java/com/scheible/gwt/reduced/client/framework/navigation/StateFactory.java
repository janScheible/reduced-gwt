package com.scheible.gwt.reduced.client.framework.navigation;

import com.scheible.gwt.reduced.client.framework.collection.JsArray;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 *
 * @author sj
 */
public class StateFactory {

    /**
     * Source: https://github.com/claudemartin/Recursive
     */
    @FunctionalInterface
    private interface RecursiveBiConsumer<T, U> {

        void accept(final T t, final U u, final BiConsumer<T, U> self);
    }

    /**
     * Source: https://github.com/claudemartin/Recursive
     */
    private static class Recursive<F> {

        private F f;

        private static <T, U> BiConsumer<T, U> biConsumer(RecursiveBiConsumer<T, U> f) {
            final Recursive<BiConsumer<T, U>> r = new Recursive<>();
            return r.f = (t, u) -> f.accept(t, u, r.f);
        }
    }

    public static States fromPresenters(AbstractPresenter<?, ?> rootPresenter, AbstractPresenter<?, ?> defaultPresenter) {
        States states = new States();
        states.setStates(JsArray.create());

        // NOTE Absolute crazy way to fake inner functions that can be called recursively and have access to the outer scope.
        RecursiveBiConsumer<Optional<String>, AbstractPresenter<?, ?>> createStatesRecursively = (Optional<String> parentStateName,
                AbstractPresenter<?, ?> currentPresenter, BiConsumer<Optional<String>, AbstractPresenter<?, ?>> self) -> {
            State currentState = new State();

            currentState.setName(currentPresenter.getName());
            parentStateName.ifPresent(name -> currentState.setParent(name));
            currentPresenter.getUrlFragment().ifPresent(urlFragment -> {
                currentState.setUrl("/" + urlFragment);
                if (urlFragment.startsWith("{")) {
                    currentState.setParamName(urlFragment.substring(1, urlFragment.indexOf(":")));
                }
            });
            currentState.setOnEnterCallback((param, href, isTarget) -> { return ((AbstractPresenter) currentPresenter).onEnter(param, href, isTarget); });
            currentState.setOnExitCallback((param, href, isSubTreeChange) -> ((AbstractPresenter) currentPresenter).onExit(param, href, isSubTreeChange));

            states.getStates().push(currentState);

            if (currentPresenter.getName().equals(defaultPresenter.getName())) {
                states.setDefaultState(currentState);
            }

            for (AbstractPresenter child : currentPresenter.getChildren()) {
                self.accept(Optional.of(currentPresenter.getName()), child);
            }
        };
        Recursive.biConsumer(createStatesRecursively).accept(Optional.empty(), rootPresenter);

        return states;
    }
}
