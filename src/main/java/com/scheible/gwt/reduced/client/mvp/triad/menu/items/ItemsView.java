package com.scheible.gwt.reduced.client.mvp.triad.menu.items;

import com.scheible.gwt.reduced.client.element.grid.Grid;
import com.scheible.gwt.reduced.client.element.grid.RendererFactory;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.collection.JsArray;
import com.scheible.gwt.reduced.client.framework.fluent.Fluent;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.forEachFlat;
import com.scheible.gwt.reduced.client.framework.mvp.ViewId;
import com.scheible.gwt.reduced.client.framework.mvp.ViewWithUiHandlers;
import com.scheible.gwt.reduced.shared.GridDataDto;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author sj
 */
class ItemsView extends ViewWithUiHandlers<ItemsUiHandlers> implements ItemsPresenter.MyView {

    private Grid<Long> grid;

    @Override
    public void initialize(Element container, List<Element> childEls, Void param, String href) {
        grid = new Grid(ViewId.getUniqueId("items-grid"), Long.class);
        grid.addRowSelectionListener(getUiHandlers()::onItemSelected);

        $(container).css("display", "flex").append(
            $(grid).css("flex-grow", "1"),
            forEachFlat(childEls, childEl -> {
                List<Fluent> elements = new ArrayList<>();
                Fluent child = $(childEl);

                if(child.hasClass(PARENT_DEFAULT_VIEW_CSS_CLASS)) {
                    elements.add($("<div>").text("No item selected.").css("flex-grow", "1")
                    .id(child.id() + "-" + PARENT_DEFAULT_VIEW_CSS_CLASS));
                    child.hide();
                }
                elements.add($(childEl).css("flex-grow", "1"));
                return elements;
            })
        );
    }

    @Override
    public void setGridData(GridDataDto gridData) {
        grid.setRenderers(JsArray.create(gridData.getColumnDefinitions().stream()
                .map(columnDefinition -> RendererFactory.createRenderer(columnDefinition.columnClassName)).collect(toList())));
        grid.setRows(JsArray.create(gridData.getColumnDefinitions().stream()
                .map(columnDefinition -> columnDefinition.title).collect(toList())), gridData.getRows());
    }

    @Override
    public void setSelectedItemId(Long itemId) {
        grid.setSelected(itemId);
    }

    @Override
    public Long getSelectedItemId() {
        return grid.getSelected();
    }
}
