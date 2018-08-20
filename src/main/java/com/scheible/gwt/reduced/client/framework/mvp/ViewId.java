package com.scheible.gwt.reduced.client.framework.mvp;

import com.scheible.gwt.reduced.client.framework.application.GwtApplication;

/**
 *
 * @author sj
 */
public class ViewId {

    private static int id = 0;

    public static String getUniqueId(String id) {
        return GwtApplication.getId() + "-" + id + "-" + (ViewId.id++);
    }
}
