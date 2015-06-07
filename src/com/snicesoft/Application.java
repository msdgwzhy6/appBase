package com.snicesoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.AVLib.LoadImg;
import com.snicesoft.http.APIs;
import com.snicesoft.http.Charset;

/**
 * 程序Application，作为应用管理配置
 * 
 * @author zhe
 *
 */
public class Application extends android.app.Application {

	private HttpUtils httpUtils;

	private BitmapUtils bitmapUtils;

	private HashMap<String, DbUtils> dbHashMap = new HashMap<String, DbUtils>();

	public HttpUtils hu() {
		return httpUtils;
	}

	public BitmapUtils bu() {
		return bitmapUtils;
	}

	public DbUtils du(String dbName) {
		if (dbHashMap.get(dbName) == null)
			dbHashMap.put(dbName, DbUtils.create(getBaseContext(), dbName));
		return dbHashMap.get(dbName);
	}

	@Override
	public void onCreate() {
		httpUtils = new HttpUtils();
		httpUtils.configTimeout(APIs.Base.TIME_OUT);
		httpUtils.configRequestThreadPoolSize(10);
		httpUtils.configResponseTextCharset(Charset.UTF_8);
		bitmapUtils = new BitmapUtils(getBaseContext());
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
