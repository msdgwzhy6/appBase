package com.apicloud.sdk;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public abstract class APICloudCallBack<T> extends RequestCallBack<String> {
	Class<T> clazz;

	public APICloudCallBack(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public void onSuccess(ResponseInfo<String> arg0) {
		onSuccess(JSON.parseObject(arg0.result, clazz));
	}

	@Override
	public void onFailure(HttpException arg0, String arg1) {

	}

	public void onSuccess(T t) {

	}
}
