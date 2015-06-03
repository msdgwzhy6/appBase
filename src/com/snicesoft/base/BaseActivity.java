package com.snicesoft.base;

import android.os.Bundle;
import android.view.View;

import com.snicesoft.Application;
import com.snicesoft.avlib.base.AvFragment;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.util.FragmentUtil;

public abstract class BaseActivity<H extends IHolder, D extends IData> extends
		AvFragmentActivity<H, D> {
	public Application getApp() {
		return (Application) getApplication();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getApp().addActivity(this);
	}

	protected AvFragment<?, ?, ?> curFragment;

	public void openFragment(int id, AvFragment<?, ?, ?> fragment) {
		if (curFragment != null && curFragment == fragment)
			return;
		FragmentUtil.openFragment(id, fragment, getSupportFragmentManager());
		curFragment = fragment;
	}

	public void replaceFragment(int id, AvFragment<?, ?, ?> fragment,
			boolean backStack) {
		FragmentUtil.replaceFragment(id, fragment, getSupportFragmentManager(),
				backStack);
		curFragment = fragment;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (curFragment != null)
			curFragment.onClick(v);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getApp().removeActivity(this);
	}
}