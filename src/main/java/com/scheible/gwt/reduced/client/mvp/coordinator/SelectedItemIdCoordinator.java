package com.scheible.gwt.reduced.client.mvp.coordinator;

import com.scheible.gwt.reduced.client.mvp.triad.menu.items.ItemsModel;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.SelectedItemModel;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.editor.ItemEditorModel;

/**
 *
 * @author sj
 */
public class SelectedItemIdCoordinator {

    public SelectedItemIdCoordinator(ItemsModel itemsModel, SelectedItemModel selectedItemModel, ItemEditorModel itemEditorModel) {
        itemsModel.selectedItemId.addChangeListener((oldValue, value) -> {
            selectedItemModel.selectedItemId.set(value);
            itemEditorModel.selectedItemId.set(value);
        });
        
        selectedItemModel.selectedItemId.addChangeListener((oldValue, value) -> {
            itemsModel.selectedItemId.set(value);
            itemEditorModel.selectedItemId.set(value);
        });
    }
}
