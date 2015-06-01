package com.snicesoft.http;

public interface APIs {
	interface Base {
		String IP = "testapi.mayiangel.com";
		String PORT = "80";
		String IMG_URL = "http://testfile.mayiangel.com/";
		String BASE_URL = "http://" + IP + ":" + PORT + "/";
		int TIME_OUT = 60 * 1000;
	}

	public interface API {
	}
}
