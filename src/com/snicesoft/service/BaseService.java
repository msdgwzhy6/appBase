package com.snicesoft.service;

import android.content.Context;

import com.snicesoft.util.NetworkUtil;

public class BaseService {
	private Context context;

	public Context getContext() {
		return context;
	}

	public BaseService(Context context) {
		this.context = context;
	}

	public boolean isNetConnect() {
		return NetworkUtil.isConnect(getContext());
	}
}
