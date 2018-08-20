package com.scheible.gwt.reduced.server.framework.collection;

import com.scheible.gwt.reduced.shared.framework.collection.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sj
 */
public class JavaArray<T> implements Array<T> {

    private ArrayList<T> internalArray;

    public JavaArray() {
        internalArray = new ArrayList<>();
    }

    public JavaArray(ArrayList<T> from) {
        internalArray = from;
    }
    
    public JavaArray(List<T> from) {
        internalArray = from instanceof ArrayList ? (ArrayList) from : new ArrayList<>(from);
    }    

    public JavaArray(JavaArray<T> from) {
        internalArray = new ArrayList<>(from.internalArray);
    }

    @Override
    public void push(T value) {
        internalArray.add(value);
    }

    @Override
    public int getLength() {
        return internalArray.size();
    }

    @Override
    public T get(int index) {
        return internalArray.get(index);
    }
}
