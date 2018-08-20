package com.scheible.gwt.reduced.client.mvp.triad.menu;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import static com.scheible.gwt.reduced.client.framework.fluent.Fluent.$;
import com.scheible.gwt.reduced.client.framework.mvp.AbstractPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.ContainerPresenter;
import com.scheible.gwt.reduced.client.framework.mvp.View;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sj
 */
public class MenuPresenter extends ContainerPresenter<View, Void> {

    public MenuPresenter(String name, Optional<String> urlFragment, AbstractPresenter... children) {
        super(name, urlFragment, Optional.empty(), children);
    }

    @Override
    protected void onInitialize(List<Element> children, Void param, String href) {
        $(getContainer().get()).css("display", "flex").css("flex-direction", "column").append(
            $("<div>").append(
				$("<a>").attr("href", "#/home").text("Home"),
				$("<span>").text(" "),
				$("<a>").attr("href", "#/items").text("Items"),
				$("<span>").text(" "),
				$("<a>").attr("href", "#/others").text("Others"),
				$("<span>").text(" ¯\\_(ツ)_/¯")
			)
        );
        children.forEach(childEl -> $(childEl).css("flex-grow", "1"));        
        $(getContainer().get()).append(children);
    }
}
