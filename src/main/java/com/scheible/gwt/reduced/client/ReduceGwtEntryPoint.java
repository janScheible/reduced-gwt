package com.scheible.gwt.reduced.client;

import com.scheible.gwt.reduced.client.element.grid.Grid;
import com.scheible.gwt.reduced.client.framework.rest.proxy.TestServiceProxy;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.scheible.gwt.reduced.client.framework.application.GwtApplication;
import com.scheible.gwt.reduced.client.framework.browser.Window;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.forEach;
import com.scheible.gwt.reduced.client.framework.error.ErrorHandler;
import com.scheible.gwt.reduced.client.framework.fluent.Fluent;
import com.scheible.gwt.reduced.client.mvp.triad.AppPresenter;
import com.scheible.gwt.reduced.client.mvp.triad.home.HomePresenter;
import com.scheible.gwt.reduced.client.mvp.triad.menu.MenuPresenter;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.ItemsPresenter;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.SelectedItemPresenter;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.editor.ItemEditorPresenter;
import com.scheible.gwt.reduced.client.mvp.triad.menu.others.OthersPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.navigation.StateFactory;
import com.scheible.gwt.reduced.client.framework.navigation.UiRouter;
import com.scheible.gwt.reduced.client.mvp.coordinator.SelectedItemIdCoordinator;
import com.scheible.gwt.reduced.client.mvp.Navigation;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.ItemsModel;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.SelectedItemModel;
import com.scheible.gwt.reduced.client.mvp.triad.menu.items.selected.editor.ItemEditorModel;
import com.scheible.gwt.reduced.shared.TestService;
import com.scheible.gwt.reduced.shared.TitleDto;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class ReduceGwtEntryPoint implements EntryPoint {
    
    private final TestService testService = new TestServiceProxy();

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(e -> Window.alert(e.getMessage())); // NOTE Seems not to work yet... :-(
        ErrorHandler.setFailureCallback(Window::alert);
        GwtApplication.init(JsBridge::init, "reduced");

        ItemsModel itemsModel = new ItemsModel();
        SelectedItemModel selectedItemModel = new SelectedItemModel();
        ItemEditorModel itemEditorModel = new ItemEditorModel();
        
        new SelectedItemIdCoordinator(itemsModel, selectedItemModel, itemEditorModel);
        
        Element rootEl = document().querySelector("div#content");
        
        HomePresenter defaultPresenter;        
        AbstractPresenter<?, ?> itemsPresenter, selectedItemPresenter, itemEditorPresenter, othersPresenter;
        AbstractPresenter rootPresenter = new AppPresenter("app", Optional.empty(), rootEl,
            defaultPresenter = new HomePresenter("home", Optional.of("home")),
            new MenuPresenter("menu", Optional.empty(), 
                itemsPresenter = new ItemsPresenter("items", Optional.of("items"), itemsModel, 
                    selectedItemPresenter = new SelectedItemPresenter("select", Optional.of("{itemId:int}"), selectedItemModel, 
                        itemEditorPresenter = new ItemEditorPresenter("edit", Optional.of("edit"), itemEditorModel)
                    )
                ),
                othersPresenter = new OthersPresenter("others", Optional.of("others"))
            )
        );
        
        Navigation.initialize(defaultPresenter, itemsPresenter, selectedItemPresenter, itemEditorPresenter, othersPresenter);
        UiRouter.initialize(StateFactory.fromPresenters(rootPresenter, defaultPresenter));

        if("aaa".equals("aaa")) {
            return;
        }

        testService.getTitle().then((Integer httpStatusCode, TitleDto body) -> {
            Grid heading = document().createElement("h1");
            heading.element().setTextContent(body.getDateTime().value + " " + body.getValue());
            document().getBody().insertBefore(heading.element(), document().getBody().getFirstChild());
        });
        
		Fluent heading;
		List<String> items = Arrays.asList("first", "second", "third");
		$(document().getBody()).append(
			$("<div>").attr("id", "my-div").text(":-)").css("background-color", "red").append(
				heading = $("<h1>").text("huhu"),
				$("<span>").text("not bad..."),
				forEach(items, item -> {
					return $("<div>").text(item).append(
						$("<span>").text(" - hi")
					);
				}),
				Optional.empty(),
				Optional.of($("<a>").attr("href", "heise.de").text("heise.de"))
			).element());
		heading.hide().show();
    }
}
