package co.alonsos.java_utilities.net_http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
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
	
	public static String urlBuilder(String host, int port, String path, List<NameValuePair> query, boolean SSL) throws Exception{
		URIBuilder url = new URIBuilder();
		try {
			if (host != null) {
				url.setHost(host);
			}else {
				url.setHost("localhost");
			}
			url.setPort(port);
			url.setPath(path);
			if (query != null) {
				url.addParameters(query);
			}
			if (SSL) {
				url.setScheme("https");
			}else {
				url.setScheme("http");
			}
		}catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		return url.toString();
	}
}
