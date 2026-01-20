package com.bukharov.url_shortener.app_service.services;

/**
 * Utility class for Base62 encoding and decoding.
 */
public class Base62 {
	private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String encode(long num) {
		StringBuilder result = new StringBuilder();
		while (num > 0) {
			result.insert(0, CHARSET.charAt((int)(num % 62)));
			num /= 62;
		}
		return result.length() > 0 ? result.toString() : "0";
	}

	public static long decode(String str) {
		long result = 0;
		for (int i = 0; i < str.length(); i++) {
			result = result * 62 + CHARSET.indexOf(str.charAt(i));
		}
		return result;
	}
}