package com.snicesoft.base;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.snicesoft.Application;
import com.snicesoft.avlib.base.AvFragment;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public abstract class BaseFragment<H extends IHolder, D extends IData, FA extends FragmentActivity>
		extends AvFragment<H, D, FA> {

	public Application getApp() {
		return (Application) fa().getApplication();
	}

	public void onClick(View v) {
		if (v == null)
			return;
	}
}