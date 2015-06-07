package com.snicesoft.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.HttpHandler.State;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

@SuppressWarnings("deprecation")
public class HttpReq {
	private Map<String, HttpHandler<String>> httpHandlerMap = new HashMap<String, HttpHandler<String>>();
	private HttpUtils httpUtils;

	public HttpReq(HttpUtils httpUtils) {
		this.httpUtils = httpUtils;
	}

	public void GET(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = new RequestParams();
		if (params != null)
			for (String key : params.keySet()) {
				rp.addQueryStringParameter(key, params.get(key).toString());
			}
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.GET, api, rp,
				callBack);
		httpHandlerMap.put(api, hh);
	}

	/**
	 * POST请求
	 * 
	 * @param api
	 *            接口地址
	 * @param params
	 *            参数
	 * @param callBack
	 */
	public void POST(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = new RequestParams();
		if (params != null)
			for (String key : params.keySet()) {
				if (params.get(key) instanceof File) {
					rp.addBodyParameter(key, (File) params.get(key));
				} else {
					rp.addBodyParameter(key, params.get(key).toString());
				}
			}
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.POST, api, rp,
				callBack);
		httpHandlerMap.put(api, hh);
	}

	/**
	 * POST请求，用InputStream的方式传递请求参数
	 * 
	 * @param api
	 *            接口地址
	 * @param requestBody
	 *            请求Body
	 * @param contentType
	 * @param callBack
	 */
	public void POST(String api, String requestBody, String contentType,
			RequestCallBack<String> callBack) {
		RequestParams params = new RequestParams();
		try {
			if (!TextUtils.isEmpty(contentType))
				params.setContentType(contentType);
			StringEntity entity = new StringEntity(requestBody, Charset.UTF_8);
			params.setBodyEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.POST, api, params,
				callBack);
		httpHandlerMap.put(api, hh);
	}

	/**
	 * POST请求，RequestBody是JSON格式
	 * 
	 * @param api
	 *            接口地址
	 * @param params
	 *            JSON的字段Map
	 * @param callBack
	 *            请求回调函数
	 */
	public void postJson(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		String requestBody = JSON.toJSONString(params);
		POST(api, requestBody, ContentType.JSON, callBack);
	}

	private void cancelHandler(HttpHandler<String> handler) {
		if (handler != null && handler.getState() != State.FAILURE
				&& handler.getState() != State.SUCCESS
				&& handler.getState() != State.CANCELLED) {
			handler.cancel();
		}
	}
}