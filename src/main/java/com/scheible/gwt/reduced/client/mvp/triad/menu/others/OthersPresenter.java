package com.scheible.gwt.reduced.client.mvp.triad.menu.others;

import com.scheible.gwt.reduced.client.element.grid.Grid;
import com.scheible.gwt.reduced.client.element.grid.RendererFactory;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.client.framework.collection.JsArray;
import com.scheible.gwt.reduced.client.framework.error.ErrorHandler;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.ContainerPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import com.scheible.gwt.reduced.client.framework.mvp.ViewId;
import com.scheible.gwt.reduced.client.framework.rest.Ajax;
import com.scheible.gwt.reduced.shared.GridDataDto;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author sj
 */
public class OthersPresenter extends ContainerPresenter<View, Void> {

    public OthersPresenter(String name, Optional<String> urlFragment, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
    }

    @Override
    protected void onInitialize(List<Element> children, Void param, String href) {
        Grid<String> grid = new Grid(ViewId.getUniqueId("others-grid"), String.class);
        Ajax.get("/simpsons", new ErrorHandler.ErrorHandledCallback<GridDataDto>() {
            @Override
            public void onSuccess(GridDataDto gridData) {
                grid.setRenderers(JsArray.create(gridData.getColumnDefinitions().stream()
                        .map(columnDefinition -> RendererFactory.createRenderer(columnDefinition.columnClassName)).collect(toList())));
                grid.setRows(JsArray.create(gridData.getColumnDefinitions().stream()
                        .map(columnDefinition -> columnDefinition.title).collect(toList())), gridData.getRows());
            }
        });
        $(getContainer().get()).append(grid).css("display", "flex");
    }
}
