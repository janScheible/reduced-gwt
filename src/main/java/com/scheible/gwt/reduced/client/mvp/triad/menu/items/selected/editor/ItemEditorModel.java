package com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.editor;

import com.scheible.gwt.reduced.client.framework.mvp.Property;

/**
 *
 * @author sj
 */
public class ItemEditorModel {
    
    private Long _selectedItemId = null;
    
    public final Property<Long> selectedItemId = new Property<>(() -> _selectedItemId, selectedItemId -> _selectedItemId = selectedItemId);
}
