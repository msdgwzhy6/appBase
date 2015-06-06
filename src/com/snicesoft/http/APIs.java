package com.snicesoft.http;

public interface APIs {
	interface Base {
		String IP = "testapi.mayiangel.com";
		String PORT = "80";
		String IMG_URL = "http://testfile.mayiangel.com/";
		String BASE_URL = "http://" + IP + ":" + PORT + "/";
		int TIME_OUT = 60 * 1000;
	}

	public interface APICloud {
		String BASE_URL = "https://d.apicloud.com";
		String APP_ID = "A6960031839242";
		String APP_KEY = "3F248D5F-50DB-782A-F437-E13796238B9E";
	}

	public interface API {
	}
}
