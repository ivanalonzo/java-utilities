package co.alonsos.java_utilities.net_http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class HTTP_Helper {
	private static Logger log = Logger.getLogger(HTTP_Helper.class);

	
	public static String encodeUrl(String url){
		try{
			return URLEncoder.encode(url, "UTF-8");
		}catch(UnsupportedEncodingException uee){
			throw new IllegalArgumentException(uee);
		}
	}
}
