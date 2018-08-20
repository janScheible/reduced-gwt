package com.scheible.gwt.reduced.client.framework.error;

import com.google.gwt.core.client.Callback;

/**
 *
 * @author sj
 */
public class ErrorHandler {

    @FunctionalInterface
    public interface SuccessCallback<T> {

        void onSuccess(T result);
    }
    
    @FunctionalInterface
    public interface FailureCallback {

        void onFailure(String reason);
    }

    private static FailureCallback failureCallback;

    public static void setFailureCallback(FailureCallback callback) {
        ErrorHandler.failureCallback = callback;
    }

    public static FailureCallback getFailureCallback() {
        return failureCallback;
    }

    public static abstract class ErrorHandledCallback<T> implements Callback<T, String>, SuccessCallback<T> {

        @Override
        public void onFailure(String reason) {
            ErrorHandler.getFailureCallback().onFailure(reason);
        }
    }
}
