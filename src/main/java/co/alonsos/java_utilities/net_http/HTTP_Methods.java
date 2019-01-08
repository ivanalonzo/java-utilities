package co.alonsos.java_utilities.net_http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import co.alonsos.java_utilities.io.IO_Utils;

/**
 * HTTP Client library with various HTTP methods implemented for easy handling of untrusted
 * connections
 * ** execGet returns a closable HTTP response
 * ** saveFile to temp allows you to save the return stream to a temporary file
 * ** execPost returns a closable HTTP response
 * 
 * @author ivanalonso
 *
 */
@SuppressWarnings("deprecation")
public class HTTP_Methods {

	private static Logger log = Logger.getLogger(HTTP_Methods.class);
	private IO_Utils fileio = new IO_Utils();

	RequestConfig timeOutConfig;

	public HTTP_Methods(int conTimeout, int sockTimeout) {
		timeOutConfig = RequestConfig.custom().setSocketTimeout(sockTimeout).setConnectTimeout(conTimeout).build();
	}

	public HTTP_Methods(int conTimeout, int sockTimeout, String proxyHost, int proxyPort, boolean https) {
		if (proxyHost != null && !proxyHost.isEmpty()) {
			HttpHost proxy;
			if (https) {
				proxy = new HttpHost(proxyHost, proxyPort, "https");
			}else {
				proxy = new HttpHost(proxyHost, proxyPort, "http");
			}
			timeOutConfig = RequestConfig.custom().setSocketTimeout(sockTimeout).setConnectTimeout(conTimeout)
			        .setProxy(proxy).build();
		}else {
			timeOutConfig = RequestConfig.custom().setSocketTimeout(sockTimeout).setConnectTimeout(conTimeout).build();
		}
	}

	private static class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private CloseableHttpClient newHTTPClient(String addr) throws KeyManagementException, NoSuchAlgorithmException {

		CloseableHttpClient httpClient;
		X509HostnameVerifier certPolicy = true ? SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
		        : SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] {
		        new DefaultTrustManager()
		}, new SecureRandom());
		SSLContext.setDefault(ctx);
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx, certPolicy);

		// If it's HTTPS, use the FULL trust SocketFactory
		if (returnWithProtocol(addr).startsWith("https")) {
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}else {
			httpClient = HttpClients.createDefault();
		}

		return httpClient;
	}

	private String returnWithProtocol(String url) {
		String protocol = "http";
		if (!url.startsWith(protocol)) {
			return protocol + "://" + url;
		}else {
			return url;
		}
	}

	private interface ResponseDataCollector<T> {
		// Process results.
		public void collectData(CloseableHttpResponse response);

		// Fetches it.
		public T getData();
	}

	@SuppressWarnings("unused")
	public Entry<CloseableHttpResponse, String> execGET(String addr, Map<String, String> headers, Integer timeout)
	        throws Exception {

		CloseableHttpClient httpclient = null;
		String response = "";

		httpclient = newHTTPClient(addr);
		HttpGet httpget = new HttpGet(returnWithProtocol(addr));
		// Timeout configuration.
		if (timeout == null) {
			httpget.setConfig(timeOutConfig);
		}else {
			RequestConfig newTimeOutConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
			        .build();
			httpget.setConfig(newTimeOutConfig);
		}
		// Headers
		if (headers != null) {
			for(String key : headers.keySet()) {
				httpget.addHeader(key, headers.get(key));
			}
		}

		CloseableHttpResponse reply = httpclient.execute(httpget);

		// Listen to response.
		HttpEntity resEntity = reply.getEntity();
		InputStream replyStream = resEntity.getContent();
		response = fileio.StreamToString(replyStream);
		log.debug("Response from (" + httpget.getURI() + ") : " + reply.getStatusLine().getStatusCode());

		// Cleanup.
		EntityUtils.consume(resEntity);
		reply.close();
		httpclient.close();
		return new AbstractMap.SimpleEntry<CloseableHttpResponse, String>(reply, response);
	}

	@SuppressWarnings("unused")
	public Entry<CloseableHttpResponse, String> saveFileToTemp(String addr, Map<String, String> headers,
	        Integer timeout, String fileExt)
	        throws Exception {
		CloseableHttpClient httpclient = null;

		httpclient = newHTTPClient(addr);
		HttpGet httpget = new HttpGet(returnWithProtocol(addr));
		// Timeout configuration.
		if (timeout == null) {
			httpget.setConfig(timeOutConfig);
		}else {
			RequestConfig newTimeOutConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
			        .build();
			httpget.setConfig(newTimeOutConfig);
		}
		// Headers
		if (headers != null) {
			for(String key : headers.keySet()) {
				httpget.addHeader(key, headers.get(key));
			}
		}

		CloseableHttpResponse reply = httpclient.execute(httpget);

		// Listen to response.
		HttpEntity resEntity = reply.getEntity();
		InputStream replyStream = resEntity.getContent();

		BufferedInputStream bis = new BufferedInputStream(replyStream);
		File tempFile = File.createTempFile("tempfile", fileExt);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
		int inByte;
		while ((inByte = bis.read()) != -1)
			bos.write(inByte);
		bis.close();
		bos.close();

		log.debug("Response from (" + httpget.getURI() + ") : " + reply.getStatusLine().getStatusCode());

		// Cleanup.
		EntityUtils.consume(resEntity);
		reply.close();
		httpclient.close();
		return new AbstractMap.SimpleEntry<CloseableHttpResponse, String>(reply, tempFile.getAbsolutePath());
	}
	
	/**
	 * Uses a Closeable HTTP Client to execute an HTTP POST Method
	 * 
	 * @param addr: In the form of http/s://some.url.com/path
	 * @param headers: a map of header/values
	 * @param entity: An HTTP_Entity object that is already built (http_entity.build)
	 * @param timeout: If null, it will use the value set in the constructor.
	 * @return
	 * @throws Exception
	 */
	public Entry<CloseableHttpResponse, String> execPOST(String addr, Map<String, String> headers, HttpEntity entity,
	        Integer timeout) throws Exception {

		String response = "";

		// Build request.
		CloseableHttpClient httpclient = newHTTPClient(addr);
		HttpPost httppost = new HttpPost(addr);

		// Timeout configuration.
		if (timeout == null) {
			httppost.setConfig(timeOutConfig);
		}else {
			RequestConfig newTimeOutConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
			        .build();
			httppost.setConfig(newTimeOutConfig);
		}

		// Add POST entity body.
		if (entity != null) {
			httppost.setEntity(entity);
		}

		// Add header info.
		if (headers != null) {
			for(String key : headers.keySet()) {
				httppost.addHeader(key, headers.get(key));
			}
		}

		CloseableHttpResponse reply = httpclient.execute(httppost);

		// Listen to response.
		HttpEntity resEntity = reply.getEntity();
		InputStream replyStream = resEntity.getContent();
		response = fileio.StreamToString(replyStream);
		log.debug("Response from (" + httppost.getURI() + ") : " + reply.getStatusLine().getStatusCode());

		// Cleanup.
		EntityUtils.consume(resEntity);
		reply.close();
		httpclient.close();

		return new AbstractMap.SimpleEntry<CloseableHttpResponse, String>(reply, response);
	}
}
