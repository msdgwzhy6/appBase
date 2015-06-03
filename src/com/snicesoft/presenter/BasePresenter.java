package com.snicesoft.presenter;

import android.content.Context;

import com.snicesoft.util.NetworkUtil;

public class BasePresenter<C extends BasePresenter.Callback> {
	public interface Callback {

	}

	private Context context;
	protected C callback;

	public void setCallback(C callback) {
		this.callback = callback;
	}

	public BasePresenter(Context context) {
		this.context = context;
	}

	public boolean isNetConnect() {
		return NetworkUtil.isConnect(getContext());
	}

	public Context getContext() {
		return context;
	}
}
