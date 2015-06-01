package com.snicesoft.service;

import android.app.Activity;

import com.snicesoft.util.NetworkUtil;

public class BaseService<A extends Activity> {
	private A activity;

	public BaseService(A activity) {
		this.activity = activity;
	}

	public A getActivity() {
		return activity;
	}

	public boolean isNetConnect() {
		return NetworkUtil.isConnect(getActivity());
	}
}
