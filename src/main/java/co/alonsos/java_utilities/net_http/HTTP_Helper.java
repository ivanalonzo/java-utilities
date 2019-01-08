package co.alonsos.java_utilities.net_http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

/**
 * These are static methods which can be used when dealing with HTTP calls
 * ** encodeURL will encode the string
 * ** urlBuilder will create a proper URI
 * **
 * 
 * @author ivanalonso
 *
 */
public class HTTP_Helper {
	private static Logger log = Logger.getLogger(HTTP_Helper.class);

	/**
	 * Given a URL, it will return a UTF-8 encoded String
	 * @param url: Encoded String
	 * @return
	 */
	public static String encodeUrl(String url){
		try{
			return URLEncoder.encode(url, "UTF-8");
		}catch(UnsupportedEncodingException uee){
			throw new IllegalArgumentException(uee);
		}
	}
	
	/**
	 * Utilizes the URIBuilder class from Apache to build a URL and return as a string
	 * @param host: Only include the host like loalhost.com
	 * @param port: Only include the port like 8080
	 * @param path: Only include the path to of the service, like /v1/path/something/
	 * @param query: This is a KV pair of queires and values
	 * @param SSL: If true it will use HTTPS
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * Does NOT use URIBuilder, it just strings the path together the parameters into a URL
	 * Unless necessary, you should use the other option
	 * @param host: Only include the host like loalhost.com
	 * @param fullPath: Include the path and any query parameters you need
	 * @param APIKey: Only include the actual key, the method will add the "apiKey" query
	 * @param SSL: If true it will use HTTPS
	 * @return
	 */
	public static String urlBuilder(String host, String fullPath, String APIKey, boolean SSL) {
		String fullURL = "";
		String apiKeyQuery = "&apiKey=";
		if (SSL) {
			fullURL = "https://";
		}else {
			fullURL = "http://";
		}
		
		if (host == null || host.isEmpty()) {
			host = "localhost";
		}
		
		if (fullPath == null || fullPath.isEmpty()) {
			apiKeyQuery = "?apiKey=";
			fullPath = "/";
		}
		
		if(APIKey == null) {
			APIKey = "";
		}
		
		fullURL += host + fullPath + apiKeyQuery + APIKey;
		return fullURL;
	}
}
