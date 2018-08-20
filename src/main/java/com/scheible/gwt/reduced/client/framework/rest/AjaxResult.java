package com.scheible.gwt.reduced.client.framework.rest;

import com.scheible.gwt.reduced.shared.framework.rest.RestCallback;
import com.scheible.gwt.reduced.shared.framework.rest.RestResult;
import com.scheible.gwt.reduced.client.framework.error.ErrorHandler;
/**
 *
 * @author sj
 */
public class AjaxResult<T> implements RestResult<T> {

	private final String url;

	public AjaxResult(String url) {
		this.url = url;
	}
	
	@Override
	public void then(RestCallback<T> callback) {
        Ajax.get(url, new ErrorHandler.ErrorHandledCallback<T>() {
            @Override
            public void onSuccess(T result) {
                callback.on(200, result);
            }            
        });
	}	
}
