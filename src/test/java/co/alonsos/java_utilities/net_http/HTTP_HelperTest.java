package co.alonsos.java_utilities.net_http;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import co.alonsos.java_utilities.net_http.HTTP_Helper;


public class HTTP_HelperTest {
	
	String host = "loalhost.com";
	int port = 1234;
	String api = "api";
	
	@Test
	public void testURLEncoder() {
		String input = "https://localhost.com:1234/api?query1=value2&query2=value2";
		String expected = "https%3A%2F%2Flocalhost.com%3A1234%2Fapi%3Fquery1%3Dvalue2%26query2%3Dvalue2";
		Assert.assertEquals(expected, HTTP_Helper.encodeUrl(input));
	}
}
