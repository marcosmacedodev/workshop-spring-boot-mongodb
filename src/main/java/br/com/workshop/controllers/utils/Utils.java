package br.com.workshop.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Utils {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

}
