package com.apicloud.sdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.HttpHandler.State;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.snicesoft.http.APIs;
import com.snicesoft.http.ContentType;

@SuppressWarnings("deprecation")
public class APICloudSDK {
	private static APICloudSDK instance;

	public static APICloudSDK getInstance(HttpUtils httpUtils) {
		if (instance == null)
			instance = new APICloudSDK(httpUtils);
		return instance;
	}

	private HttpUtils httpUtils;
	private Map<String, HttpHandler<String>> httpHandlerMap = new HashMap<String, HttpHandler<String>>();

	private APICloudSDK(HttpUtils httpUtils) {
		this.httpUtils = httpUtils;
	}

	public void GET(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = getRP(params);
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.GET,
				APIs.APICloud.BASE_URL + api, rp, callBack);
		httpHandlerMap.put(api, hh);
	}

	public void POST(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = getRP(params);
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.POST,
				APIs.APICloud.BASE_URL + api, rp, callBack);
		httpHandlerMap.put(api, hh);
	}

	public void PUT(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = getRP(params);
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.PUT,
				APIs.APICloud.BASE_URL + api, rp, callBack);
		httpHandlerMap.put(api, hh);
	}

	public void DELETE(String api, Map<String, Object> params,
			RequestCallBack<String> callBack) {
		RequestParams rp = getRP(params);
		cancelHandler(httpHandlerMap.get(api));
		HttpHandler<String> hh = httpUtils.send(HttpMethod.DELETE,
				APIs.APICloud.BASE_URL + api, rp, callBack);
		httpHandlerMap.put(api, hh);
	}

	private RequestParams getRP(Map<String, Object> params) {
		RequestParams rp = new RequestParams();
		try {
			rp.setContentType(ContentType.JSON);
			if (params != null) {
				String requestBody = JSON.toJSONString(params);
				StringEntity entity = new StringEntity(requestBody, HTTP.UTF_8);
				rp.setBodyEntity(entity);
			}
			rp.addHeader("X-APICloud-AppId", APIs.APICloud.APP_ID);
			rp.addHeader("X-APICloud-AppKey", getYourAppKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rp;
	}

	private void cancelHandler(HttpHandler<String> handler) {
		if (handler != null && handler.getState() != State.FAILURE
				&& handler.getState() != State.SUCCESS
				&& handler.getState() != State.CANCELLED) {
			handler.cancel();
		}
	}

	private String getYourAppKey() {
		long now = System.currentTimeMillis();
		return SHA1(APIs.APICloud.APP_ID + "UZ" + APIs.APICloud.APP_KEY + "UZ"
				+ now)
				+ "." + now;
	}

	private String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}