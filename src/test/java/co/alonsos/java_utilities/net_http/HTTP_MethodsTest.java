package co.alonsos.java_utilities.net_http;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.BinaryBody.binary;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.RegexBody.regex;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpStatusCode;
import org.mockserver.model.Parameter;

public class HTTP_MethodsTest {
	private static Logger log = Logger.getLogger(HTTP_MethodsTest.class);

	private ClientAndServer mockServer;
	int serverPort = 9999;
	int clientPort = 8888;
	HTTP_Methods http;
	String expHeader = "Token";
	String expHeaderValue = "Newtokenvalue";
	JSONObject jsonRes = null;
	int timeout = 5000;
	String bodyKey = "foo";
	String bodyValue = "bar";

	@BeforeEach
	public void setupMock() {
		mockServer = startClientAndServer(serverPort);
		http = new HTTP_Methods(timeout, timeout);
	}

	@AfterEach
	public void tearDownMock() {
		mockServer.stop();
		http = null;
	}

	/*
	 * Simple GET Test
	 */
	@Test
	public void testGet() throws Exception {
		String endPoint = "/get";
		String testURL = "http://localhost:" + serverPort + endPoint;
		mockServer.when(request().withPath(endPoint)).respond(response().withBody("OK"));
		// Make a call to the endpoint and confirm we get a 200
		Entry<CloseableHttpResponse, String> response = http.execGET(testURL, null, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Test GET and setting a header
	 */
	@Test
	public void testGeWithHeadert() throws Exception {
		String headersGet = "/headers";
		String testURL = "http://localhost:" + serverPort + headersGet;
		Header header = new Header(expHeader, expHeaderValue);
		mockServer.when(request().withPath(headersGet).withHeader(header)).respond(response().withBody("OK"));

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);

		// If we sent the expected headers we should get a 200
		Entry<CloseableHttpResponse, String> response = http.execGET(testURL, headers, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
		// Confirm we actually sent the headers in the previous call by emmitting it now and confirming we
		// get a non-200
		response = http.execGET(testURL, null, null);
		Assert.assertEquals(404, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Test GET and setting multiple headers
	 */
	@Test
	public void testGetWithHeaders() throws Exception {
		String headersGet = "/headers";
		String testURL = "http://localhost:" + serverPort + headersGet;
		Header header1 = new Header(expHeader, expHeaderValue);
		Header header2 = new Header(expHeaderValue, expHeader);
		mockServer.when(request().withPath(headersGet).withHeader(header1).withHeader(header2))
		        .respond(response().withBody("OK"));

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);

		// If we sent the expected headers we should get a 200
		Entry<CloseableHttpResponse, String> response = http.execGET(testURL, headers, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Test GET with query parameters
	 */
	@Test
	public void testGetAPIParams() throws Exception {
		String paramsGet = "/params";
		String testURL = "http://localhost:" + serverPort + paramsGet + "?arg=value";
		Parameter expParams = new Parameter("arg", "value");
		mockServer.when(request().withPath(paramsGet).withQueryStringParameter(expParams))
		        .respond(response().withBody("OK"));

		// If we sent the expected headers we should get a 200
		Entry<CloseableHttpResponse, String> response = http.execGET(testURL, null, null);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Make sure that basic POST works
	 */
	@Test
	public void testPost() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint;
		mockServer.when(request().withMethod("POST").withPath(endPoint)).respond(response().withBody("OK"));

		// If we sent the expected headers we should get a 200
		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, null, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Check POST and one request header works
	 */
	@Test
	public void testPostWithHeader() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint;
		Header header = new Header(expHeader, expHeaderValue);
		mockServer.when(request().withMethod("POST").withPath(endPoint).withHeader(header))
		        .respond(response().withBody("OK"));

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, headers, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());

	}

	/*
	 * Check POST and multiple requests headers work
	 */
	@Test
	public void testPostWithHeaders() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint;
		Header header1 = new Header(expHeader, expHeaderValue);
		Header header2 = new Header(expHeaderValue, expHeader);

		mockServer.when(request().withMethod("POST").withPath(endPoint).withHeader(header1).withHeader(header2))
		        .respond(response().withBody("OK"));

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);

		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, headers, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Check POST with a body
	 */
	@Test
	public void testPostWithBody() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint;
		mockServer
		        .when(request().withMethod("POST").withPath(endPoint)
		                .withBody(regex("[\\n.\\w\\W]*" + bodyKey + "[\\n.\\w\\W]*" + bodyValue + "[\\n.\\w\\W]*")))
		        .respond(response().withBody("OK"));

		MultipartEntityBuilder body = MultipartEntityBuilder.create();
		body.addPart(bodyKey, new StringBody(bodyValue, ContentType.DEFAULT_TEXT));

		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, null, body.build(), timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Check POST with a body and headers in the request
	 */
	@Test
	public void testPostWithBodyAndHeaders() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint;
		Header header1 = new Header(expHeader, expHeaderValue);
		Header header2 = new Header(expHeaderValue, expHeader);
		mockServer
		        .when(request().withMethod("POST").withPath(endPoint).withHeader(header1).withHeader(header2)
		                .withBody(regex("[\\n.\\w\\W]*" + bodyKey + "[\\n.\\w\\W]*" + bodyValue + "[\\n.\\w\\W]*")))
		        .respond(response().withBody("OK"));

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(expHeader, expHeaderValue);
		headers.put(expHeaderValue, expHeader);

		MultipartEntityBuilder body = MultipartEntityBuilder.create();
		body.addPart(bodyKey, new StringBody(bodyValue, ContentType.DEFAULT_TEXT));

		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, headers, body.build(), timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	/*
	 * Test POST with arguments
	 */
	@Test
	public void testPostAPIArgs() throws Exception {
		String endPoint = "/post";
		String testURL = "http://localhost:" + serverPort + endPoint + "?arg=value";
		Parameter expParams = new Parameter("arg", "value");
		mockServer.when(request().withMethod("POST").withPath(endPoint).withQueryStringParameter(expParams))
		        .respond(response().withBody("OK"));

		Entry<CloseableHttpResponse, String> response = http.execPOST(testURL, null, null, timeout);
		Assert.assertEquals(200, response.getKey().getStatusLine().getStatusCode());
	}

	@Test
	public void testGetAPIImageJPG() throws Exception {
		String filePath = "src/test/resources/unit_test_files/temp.jpg";
		String endPoint = "/image/jpg";
		byte[] jpgBytes = IOUtils.toByteArray(new FileInputStream(filePath));
		mockServer.when(request().withPath(endPoint))
		        .respond(response().withStatusCode(HttpStatusCode.OK_200.code()).withBody(binary(jpgBytes)));

		String testURL = "http://localhost:" + serverPort + endPoint;
		Entry<CloseableHttpResponse, String> response = http.saveFileToTemp(testURL, null, 10000, ".jpg");
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
		// Read the file we stored and the file that was saved
		File expected = new File(filePath);
		File actual = new File(response.getValue());
		// If this fails, it could be because the downloaded file (under src...) could be different than
		// the file actually being downloaded
		Assert.assertEquals(expected.length(), actual.length());
		FileUtils.deleteQuietly(actual);
	}

	@Test
	public void testGetAPIImagePNG() throws Exception {
		String filePath = "src/test/resources/unit_test_files/temp.png";
		String endPoint = "/image/png";
		byte[] pngBytes = IOUtils.toByteArray(new FileInputStream(filePath));
		mockServer.when(request().withPath(endPoint))
		        .respond(response().withStatusCode(HttpStatusCode.OK_200.code()).withBody(binary(pngBytes)));

		String testURL = "http://localhost:" + serverPort + endPoint;
		Entry<CloseableHttpResponse, String> response = http.saveFileToTemp(testURL, null, 10000, ".png");
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
		// Read the file we stored and the file that was saved
		File expected = new File(filePath);
		File actual = new File(response.getValue());
		// If this fails, it could be because the downloaded file (under src...) could be different than
		// the file actually being downloaded
		Assert.assertEquals(expected.length(), actual.length());
		FileUtils.deleteQuietly(actual);
	}

	@Test
	public void testGetAPIImageSVG() throws Exception {
		String filePath = "src/test/resources/unit_test_files/temp.svg";
		String endPoint = "/image/svg";
		byte[] svgBytes = IOUtils.toByteArray(new FileInputStream(filePath));
		mockServer.when(request().withPath(endPoint))
		        .respond(response().withStatusCode(HttpStatusCode.OK_200.code()).withBody(binary(svgBytes)));

		String testURL = "http://localhost:" + serverPort + endPoint;
		Entry<CloseableHttpResponse, String> response = http.saveFileToTemp(testURL, null, 10000, ".svg");
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
		// Read the file we stored and the file that was saved
		File expected = new File(filePath);
		File actual = new File(response.getValue());
		// If this fails, it could be because the downloaded file (under src...) could be different than
		// the file actually being downloaded
		Assert.assertEquals(expected.length(), actual.length());
		FileUtils.deleteQuietly(actual);
	}

	@Test
	public void testGetAPIImageWEBP() throws Exception {
		String filePath = "src/test/resources/unit_test_files/temp.webp";
		String endPoint = "/image/webp";
		byte[] webpBytes = IOUtils.toByteArray(new FileInputStream(filePath));
		mockServer.when(request().withPath(endPoint))
		        .respond(response().withStatusCode(HttpStatusCode.OK_200.code()).withBody(binary(webpBytes)));

		String testURL = "http://localhost:" + serverPort + endPoint;
		Entry<CloseableHttpResponse, String> response = http.saveFileToTemp(testURL, null, 10000, ".webp");
		Assert.assertEquals(response.getKey().getStatusLine().getStatusCode(), 200);
		// Read the file we stored and the file that was saved
		File expected = new File(filePath);
		File actual = new File(response.getValue());
		// If this fails, it could be because the downloaded file (under src...) could be different than
		// the file actually being downloaded
		Assert.assertEquals(expected.length(), actual.length());
		FileUtils.deleteQuietly(actual);
	}
}
