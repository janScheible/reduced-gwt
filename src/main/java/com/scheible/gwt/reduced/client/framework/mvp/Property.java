package com.scheible.gwt.reduced.client.framework.mvp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author sj
 */
public class Property<T> {

    @FunctionalInterface
    public static interface ChangeListener<T> {

        void onChange(T oldValue, T value);
    }

    private final Supplier<T> getter;
    private final Consumer<T> setter;

    private final List<ChangeListener<T>> listeners = new ArrayList<>();

    public Property(Supplier<T> getter, Consumer<T> setter) {
        this.getter = getter;
        this.setter = setter;
    }
    
    public Property(Supplier<T> getter) {
        this.getter = getter;
        this.setter = null;
    }

    public void addChangeListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    private void fire(T oldValue, T value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(oldValue, value);
        }
    }

    public void set(T value) {
        if(setter == null) {
            throw new IllegalStateException("Read-only property!");
        }
        
        T oldValue = getter.get();
        if (!(oldValue == null && value == null)
                && !(oldValue != null && oldValue.equals(value))) {
            setter.accept(value);
            fire(oldValue, value);
        }
    }

    public T get() {
        return getter.get();
    }
}
