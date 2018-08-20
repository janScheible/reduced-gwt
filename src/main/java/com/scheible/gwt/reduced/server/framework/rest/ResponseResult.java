package com.scheible.gwt.reduced.server.framework.rest;

import com.scheible.gwt.reduced.shared.framework.rest.RestCallback;
import com.scheible.gwt.reduced.shared.framework.rest.RestResult;
import org.springframework.http.HttpStatus;

/**
 *
 * @author sj
 */
public class ResponseResult<T> implements RestResult<T> {
	
	private final HttpStatus httpStatus;
	private final T body;

	public ResponseResult(HttpStatus httpStatus, T body) {
		this.httpStatus = httpStatus;
		this.body = body;
	}

	@Override
	public void then(RestCallback<T> callback) {
		throw new UnsupportedOperationException("Not supported on server-side.");
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public T getBody() {
		return body;
	}
}
