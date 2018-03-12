package co.alonsos.java_utilities.net_http;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HTTP_PostTest {
	private static Logger log = Logger.getLogger(HTTP_PostTest.class);
	int timeout = 5000;
	String testUrl = "https://httpbin.org/post";
	String expHeader = "Token";
	String expHeaderValue = "Newtokenvalue";
	String bodyKey = "foo";
	String bodyValue = "bar";
	HTTP_Post post = null;
	JSONObject jsonRes = null;
	
	@BeforeEach
	public void setup() {
		post = new HTTP_Post(timeout, timeout);
	}
	
	/*
	 * Make sure that basic POST works
	 */
	@Test
	public void testPost() throws Exception{
		Entry<CloseableHttpResponse, String> response = post.execPOST(testUrl, null, null, timeout);
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
	}
	
	/*
	 * Check POST and one request header works 
	 */
	@Test
	public void testPostWithHeader() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		
		Entry<CloseableHttpResponse, String> response = post.execPOST(testUrl, headers, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
	}
	
	/*
	 * Check POST and multiple requests headers work 
	 */
	@Test
	public void testPostWithHeaders() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);
		
		Entry<CloseableHttpResponse, String> response = post.execPOST(testUrl, headers, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
		Assert.assertEquals(expHeader, jsonRes.getJSONObject("headers").get(expHeaderValue));
	}
	
	/*
	 * Check POST with a body 
	 */
	@Test
	public void testPostWithBody() throws Exception{
		MultipartEntityBuilder body = MultipartEntityBuilder.create();
		body.addPart(bodyKey, new StringBody(bodyValue, ContentType.DEFAULT_TEXT));
		
		Entry<CloseableHttpResponse, String> response = post.execPOST(testUrl, null, body.build(), timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(bodyValue, jsonRes.getJSONObject("form").get(bodyKey));
	}
	
	/*
	 * Check POST with a body and headers in the request
	 */
	@Test
	public void testPostWithBodyAndHeaders() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);
		
		MultipartEntityBuilder body = MultipartEntityBuilder.create();
		body.addPart(bodyKey, new StringBody(bodyValue, ContentType.DEFAULT_TEXT));
		
		Entry<CloseableHttpResponse, String> response = post.execPOST(testUrl, headers, body.build(), timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(bodyValue, jsonRes.getJSONObject("form").get(bodyKey));
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
		Assert.assertEquals(expHeader, jsonRes.getJSONObject("headers").get(expHeaderValue));
	}
}
