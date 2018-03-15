package co.alonsos.java_utilities.net_http;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import co.alonsos.java_utilities.net_http.HTTP_Helper;

import java.util.ArrayList;
import java.util.List;



public class HTTP_HelperTest {
	
	@Test
	public void testURLEncoder() {
		String input = "https://localhost.com:1234/api?query1=value2&query2=value2";
		String expected = "https%3A%2F%2Flocalhost.com%3A1234%2Fapi%3Fquery1%3Dvalue2%26query2%3Dvalue2";
		Assert.assertEquals(expected, HTTP_Helper.encodeUrl(input));
	}
	
	@Test
	public void testURLBuilder() throws Exception {
		String expected = "https://localhost.com:1234/api?query1=value1&query2=value2";
		String host = "localhost.com";
		int port = 1234;
		String path = "api";
		List<NameValuePair> q = new ArrayList<NameValuePair>();
		q.add(new BasicNameValuePair("query1", "value1"));
		q.add(new BasicNameValuePair("query2", "value2"));
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, port, path, q, true));
	}
	
	@Test
	public void testURLBuilderLongPath() throws Exception {
		String expected = "https://localhost.com:1234/v1/api?query1=value1&query2=value2";
		String host = "localhost.com";
		int port = 1234;
		String path = "v1/api";
		List<NameValuePair> q = new ArrayList<NameValuePair>();
		q.add(new BasicNameValuePair("query1", "value1"));
		q.add(new BasicNameValuePair("query2", "value2"));
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, port, path, q, true));
	}
	
	@Test
	public void testURLBuilderNullPath() throws Exception {
		String expected = "https://localhost.com:1234?query1=value1&query2=value2";
		String host = "localhost.com";
		int port = 1234;
		List<NameValuePair> q = new ArrayList<NameValuePair>();
		q.add(new BasicNameValuePair("query1", "value1"));
		q.add(new BasicNameValuePair("query2", "value2"));
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, port, null, q, true));
	}
	
	@Test
	public void testURLBuilderNullQuery() throws Exception {
		String expected = "https://localhost.com:1234/api";
		String host = "localhost.com";
		int port = 1234;
		String path = "api";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, port, path, null, true));
	}
	
	@Test
	public void testURLBuilderNoSSL() throws Exception {
		String expected = "http://localhost.com:1234/api?query1=value1&query2=value2";
		String host = "localhost.com";
		int port = 1234;
		String path = "api";
		List<NameValuePair> q = new ArrayList<NameValuePair>();
		q.add(new BasicNameValuePair("query1", "value1"));
		q.add(new BasicNameValuePair("query2", "value2"));
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, port, path, q, false));
	}
	
	@Test
	public void testURLBuilderNullHost() throws Exception {
		String expected = "https://localhost:1234/api";
		int port = 1234;
		String path = "api";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(null, port, path, null, true));
	}
	
	@Test
	public void testURLBuilderCustom()throws Exception {
		String expected = "https://localhost.com/v2/everything?domains=nationalgeographic.com&apiKey=longApiKey123";
		String host = "localhost.com";
		String path = "/v2/everything?domains=nationalgeographic.com";
		String APIKey = "longApiKey123";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, path, APIKey, true));
	}
	
	@Test
	public void testURLBuilderCustomNoSSL()throws Exception {
		String expected = "http://localhost.com/v2/everything?domains=nationalgeographic.com&apiKey=longApiKey123";
		String host = "localhost.com";
		String path = "/v2/everything?domains=nationalgeographic.com";
		String APIKey = "longApiKey123";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(host, path, APIKey, false));
	}
	
	@Test
	public void testURLBuilderCustomNoHost()throws Exception {
		String expected = "http://localhost/v2/everything?domains=nationalgeographic.com&apiKey=longApiKey123";
		String path = "/v2/everything?domains=nationalgeographic.com";
		String APIKey = "longApiKey123";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(null, path, APIKey, false));
	}
	
	@Test
	public void testURLBuilderCustomNoPath()throws Exception {
		String expected = "http://localhost/?apiKey=longApiKey123";
		String APIKey = "longApiKey123";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(null, null, APIKey, false));
	}
	
	@Test
	public void testURLBuilderCustomNoKey()throws Exception {
		String expected = "http://localhost/v2/everything?domains=nationalgeographic.com&apiKey=";
		String path = "/v2/everything?domains=nationalgeographic.com";
		Assert.assertEquals(expected, HTTP_Helper.urlBuilder(null, path, null, false));
	}
}
