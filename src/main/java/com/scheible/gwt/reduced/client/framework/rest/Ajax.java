package com.scheible.gwt.reduced.client.framework.rest;

import com.scheible.gwt.reduced.client.framework.browser.Window;
import com.scheible.gwt.reduced.client.framework.browser.request.XMLHttpRequest;
import com.scheible.gwt.reduced.client.framework.error.ErrorHandler.ErrorHandledCallback;

/**
 *
 * @author sj
 */
public class Ajax {
    
    public static void get(String url, ErrorHandledCallback callback) {
        XMLHttpRequest request = new XMLHttpRequest();
        request.open("GET", url, true);
        request.setOnReadyStateChange(event -> {
            int state = request.getReadyState();
            if(state == 4) {
                if(request.getStatus() == 200) {
                    callback.onSuccess(Window.json().parse(request.getResponseText()));
                } else {
                    callback.onFailure(request.getStatusText());
                }
            }
        });
        request.send(null);        
    }
}
