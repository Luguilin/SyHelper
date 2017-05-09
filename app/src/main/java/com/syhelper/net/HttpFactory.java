package com.syhelper.net;

public class HttpFactory {

	public static final String Get="GET";
	public static final String Post="POST";
	public static HttpClient buildClient(String method) {
		switch (method) {
		case Post:
			return new OKHttpPost();
		default:
			return new OKHttpGet();
		}
	}
}
