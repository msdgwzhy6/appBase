package com.snicesoft.http;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.snicesoft.service.ServiceCallback;

public abstract class HttpCallback extends RequestCallBack<String> {
	private ServiceCallback<?> callback;

	public HttpCallback(ServiceCallback<?> callback) {
		this.callback = callback;
	}

	@Override
	public void onStart() {
		if (callback != null)
			callback.onStart();
	}

	@Override
	public void onLoading(long total, long current, boolean isUploading) {
		if (callback != null)
			callback.onLoading(total, current, isUploading);
	}

	@Override
	public void onFailure(HttpException error, String msg) {
		if (callback != null)
			callback.onFailure(error, msg);
	}
}
