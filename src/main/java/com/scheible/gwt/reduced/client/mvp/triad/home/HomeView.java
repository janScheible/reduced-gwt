package com.scheible.gwt.reduced.client.mvp.triad.home;

import com.scheible.gwt.reduced.client.element.TileButton;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.ViewId;
import com.scheible.gwt.reduced.client.framework.mvp.ViewWithUiHandlers;
import com.scheible.gwt.reduced.client.mvp.Navigation;
import java.util.List;

/**
 *
 * @author sj
 */
class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

    @Override
    public void initialize(Element container, List<Element> children, Void param, String href) {
        $(container).append(
            $(new TileButton.Builder(ViewId.getUniqueId("home-items-button"), "Items", Navigation.getItemsHref())
                    .setBackgroundColor("#1E63B0").setFontColor("#ffffff").build()).css("margin-right", "8px"),
            new TileButton.Builder(ViewId.getUniqueId("home-others-button"), "Others", Navigation.getOthersHref())
                    .setBackgroundColor("#009169").setFontColor("#ffffff").build()
        );        
    }    
}
