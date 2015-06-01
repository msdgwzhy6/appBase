package com.snicesoft.service;

import com.lidroid.xutils.exception.HttpException;

public abstract class ServiceCallback<T> {

	public void onLoading(long total, long current, boolean isUploading) {
	}

	public abstract void onSuccess(T t, boolean isCache);

	public void onFailure(HttpException error, String msg) {
	}

	public void onStart() {
	}
}
