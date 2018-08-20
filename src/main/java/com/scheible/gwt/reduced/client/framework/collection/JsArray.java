package com.scheible.gwt.reduced.client.framework.collection;

import com.scheible.gwt.reduced.shared.framework.collection.Array;
import com.scheible.gwt.reduced.shared.framework.collection.JsArrayHelper;

/**
 *
 * @author sj
 */
public class JsArray {

    private JsArray() {
    }

    public static <T extends Object> Array<T> create() {
        return JsArrayHelper.createArray();
    }

    public static <T extends Object> Array<T> create(Iterable<T> srcCollection) {
        Array<T> a = create();

        for (T v : srcCollection) {
            a.push(v);
        }
        return a;
    }
}
