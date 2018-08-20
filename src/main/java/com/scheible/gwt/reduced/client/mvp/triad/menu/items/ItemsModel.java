package com.scheible.gwt.reduced.client.mvp.triad.menu.items;

import com.scheible.gwt.reduced.client.framework.browser.promise.Promise;
import com.scheible.gwt.reduced.client.framework.browser.promise.Rejector;
import com.scheible.gwt.reduced.client.framework.browser.promise.Resolver;
import com.scheible.gwt.reduced.client.framework.mvp.Property;
import com.scheible.gwt.reduced.client.framework.rest.proxy.TestServiceProxy;
import com.scheible.gwt.reduced.shared.GridDataDto;
import com.scheible.gwt.reduced.shared.TestService;

/**
 *
 * @author sj
 */
public class ItemsModel {
    
    private final TestService testService = new TestServiceProxy();

    private Long _selectedItemId = null;
    public final Property<Long> selectedItemId = new Property<>(() -> _selectedItemId, selectedItemId -> _selectedItemId = selectedItemId);
    
    final Property<Promise<GridDataDto, Void>> gridDate = new Property<>(() -> {
        Promise<GridDataDto, Void> promise = new Promise<>((Resolver<GridDataDto> resolver, Rejector<Void> rejecter) -> {
            testService.getGridData().then((Integer httpStatusCode, GridDataDto body) -> {
                resolver.resolve(body);
            });
        });
       return promise; 
    });
}
