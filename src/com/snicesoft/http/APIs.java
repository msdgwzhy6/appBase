package com.snicesoft.http;

public interface APIs {
	interface Base {
		String IMG_URL = "http://img.snicesoft.com/";
		String BASE_URL = "http://api.snicesoft.com/";
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
