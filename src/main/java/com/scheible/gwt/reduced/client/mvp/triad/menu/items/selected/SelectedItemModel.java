package com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected;

import com.scheible.gwt.reduced.client.framework.mvp.Property;

/**
 *
 * @author sj
 */
public class SelectedItemModel {

    private Long _selectedItemId = null;

    public final Property<Long> selectedItemId = new Property<>(() -> _selectedItemId, selectedItemId -> _selectedItemId = selectedItemId);
}
