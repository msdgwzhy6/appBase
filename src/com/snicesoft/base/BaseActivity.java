package com.snicesoft.base;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.snicesoft.Application;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.util.FragmentUtil;

public abstract class BaseActivity<H extends IHolder, D extends IData> extends
		AvFragmentActivity<H, D> implements OnClickListener {
	public Application getApp() {
		return (Application) getApplication();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApp().addActivity(this);
	}

	protected BaseFragment<?, ?, ?> curFragment;

	protected void openFragment(int id, BaseFragment<?, ?, ?> fragment) {
		FragmentUtil.openFragment(this, id, fragment);
		curFragment = fragment;
	}

	@Override
	public void onClick(View v) {
		if (v == null)
			return;
	}

	@Override
	protected void onDestroy() {
		getApp().removeActivity(this);
	}
}