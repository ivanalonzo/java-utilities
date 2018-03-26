package co.alonsos.java_utilities.rest;

/**
 * This is a constants class for varying types of Response Constants and the-like information
 * 
 * @author ivana
 */
public class ResponseConstants {

	public static final String MSG_ERROR = "An Error or Exception has occured";
	public static final String MSG_SUCCESS = "OK";
	public static final String MSG_BADINPUT = "Bad or missing input. Please try again";

	public static final int HTTP_OK = 200; // All OK, there were no issues
	public static final int HTTP_ERROR = 400; // There was an exception that was caught by us. We may or not expose the
	                                          // actual exception
	public static final int HTTP_BAD_PARAM = 412; // The request didn't include the correct parameters. This may be
	                                              // attached to the "invalidInput" or "emptyInp
	public static final int HTTP_UNAUTHORIZED = 401; // token or timestamp is not valid in request
	public static final int HTTP_SERVICE_GONE = 410; // service is going to be terminated

	public static final String OK = "OK";
	public static final String ERROR = "error"; // duplicated in DBconstants

	public static final String INVALID_INPUT = "One or more of the parameters needed by this API are either empty or invalid. Please try again";
	public static final String DEPRECATED_API = "This API is deprecated.";
	public static final String EXPERIMENTAL_API = "This API is currently in experimentation.";

	public static final String NOT_PERMITTED = "You don't have permission";
}

