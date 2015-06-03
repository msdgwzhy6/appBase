package com.snicesoft;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.AVLib.LoadImg;

/**
 * 程序Application，作为应用管理配置
 * 
 * @author zhe
 *
 */
public class Application extends android.app.Application {

	private static BitmapUtils bitmapUtils;
	private static DbUtils dbUtils;

	public static BitmapUtils bt() {
		return bitmapUtils;
	}

	public static DbUtils dt() {
		return dbUtils;
	}

	/**
	 * 
	 * @param message
	 * @param flag
	 * <br>
	 *            [0]:Cancelable <br>
	 *            [1]:CanceledOnTouchOutside
	 */

	@Override
	public void onCreate() {
		bitmapUtils = new BitmapUtils(getBaseContext());
		dbUtils = DbUtils.create(getBaseContext(), "app.db");
		AVLib.setLoadImg(new LoadImg() {
			@SuppressWarnings("deprecation")
			@Override
			public void loadImg(View v, int loading, int fail, String uri) {
				BitmapDisplayConfig config = new BitmapDisplayConfig();
				if (loading != 0) {
					Drawable drawable = getResources().getDrawable(loading);
					config.setLoadingDrawable(drawable);
				}
				if (fail != 0) {
					Drawable drawable = getResources().getDrawable(fail);
					config.setLoadFailedDrawable(drawable);
				}
				bitmapUtils.display(v, uri, config);
			}
		});
	}

	private List<Activity> activities = new ArrayList<Activity>();

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void removeActivity(Activity activity) {
		if (activities.contains(activity)) {
			activities.remove(activity);
		}
	}

	public void finishActivity(Class<?> activity) {
		for (Activity act : activities) {
			if (act.getClass() == activity) {
				act.finish();
				break;
			}
		}
	}

	public void exitApp() {
		for (Activity activity : activities) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
}
