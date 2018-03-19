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

public class HTTP_MethodsTest {
	private static Logger log = Logger.getLogger(HTTP_MethodsTest.class);
	int timeout = 5000;
	String testUrlPost = "https://httpbin.org/post";
	String testUrlGet = "https://httpbin.org/get";
	String expHeader = "Token";
	String expHeaderValue = "Newtokenvalue";
	String bodyKey = "foo";
	String bodyValue = "bar";
	HTTP_Methods http = null;
	JSONObject jsonRes = null;
	
	@BeforeEach
	public void setup() {
		http = new HTTP_Methods(timeout, timeout);
	}
	
	/*
	 * Make sure that basic POST works
	 */
	@Test
	public void testPost() throws Exception{
		Entry<CloseableHttpResponse, String> response = http.execPOST(testUrlPost, null, null, timeout);
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
	}
	
	/*
	 * Check POST and one request header works 
	 */
	@Test
	public void testPostWithHeader() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		
		Entry<CloseableHttpResponse, String> response = http.execPOST(testUrlPost, headers, null, timeout);
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
		
		Entry<CloseableHttpResponse, String> response = http.execPOST(testUrlPost, headers, null, timeout);
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
		
		Entry<CloseableHttpResponse, String> response = http.execPOST(testUrlPost, null, body.build(), timeout);
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
		
		Entry<CloseableHttpResponse, String> response = http.execPOST(testUrlPost, headers, body.build(), timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(bodyValue, jsonRes.getJSONObject("form").get(bodyKey));
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
		Assert.assertEquals(expHeader, jsonRes.getJSONObject("headers").get(expHeaderValue));
	}
	
	/*
	 * Test POST with arguments
	 */
	@Test
	public void testPostAPIArgs() throws Exception{
		String url = testUrlPost + "?arg=value";
		Entry<CloseableHttpResponse, String> response = http.execPOST(url, null, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals("value", jsonRes.getJSONObject("args").get("arg"));
	}
	
	/*
	 * Simple GET Test
	 */
	@Test
	public void testGet() throws Exception{
		Entry<CloseableHttpResponse, String> response = http.execGET(testUrlGet, null, null);
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
	}
	
	/*
	 * Test GET and setting a header
	 */
	@Test
	public void testGeWithHeadert() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		
		Entry<CloseableHttpResponse, String> response = http.execGET(testUrlGet, headers, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
	}
	
	/*
	 * Test GET and multiple headers
	 */
	@Test
	public void testGetWithHeaders() throws Exception{
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);
		
		Entry<CloseableHttpResponse, String> response = http.execGET(testUrlGet, headers, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals(expHeaderValue, jsonRes.getJSONObject("headers").get(expHeader));
		Assert.assertEquals(expHeader, jsonRes.getJSONObject("headers").get(expHeaderValue));
	}
	
	/*
	 * Test GET and query parms in the call
	 */
	@Test
	public void testGetAPIParams() throws Exception{
		String url = testUrlGet + "?arg=value";
		Entry<CloseableHttpResponse, String> response = http.execGET(url, null, null);
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
		jsonRes = new JSONObject(response.getValue());
		Assert.assertEquals("value", jsonRes.getJSONObject("args").get("arg"));
	}
}
