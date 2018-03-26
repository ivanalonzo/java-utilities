package co.alonsos.java_utilities.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import com.google.gson.Gson;

/**
 * This is a container object to hold varying types of JSON response information.
 * 
 * @author ivana
 */
public class S_Response {

	protected int httpcode;
	protected String message;
	protected Object data;

	// private static Gson gson = new
	// GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
	private static Gson gson = new Gson();

	/**
	 * Not for outside use.
	 */
	private S_Response(int httpCode, String message, Object data) {
		this.httpcode = httpCode;
		this.message = message;
		this.data = data;
	}

	/**
	 * Not for outside use.
	 */
	@SuppressWarnings("unused")
	private Response toResponse() {
		return Response.status(this.httpcode).entity(gson.toJson(this)).build();
	}

	/**
	 * HTTP 200 response.
	 * 
	 * @param msg
	 *            string message, recommended.
	 * @param data
	 *            converted to JSON data, not required.
	 * @return the response
	 */
	public static Response makeOKResponse(String msg, Object data) {
		S_Response resp = new S_Response(ResponseConstants.HTTP_OK, msg, data);
		return resp.toResponse();
	}

	/**
	 * Will set the response to 200 and message as OK
	 * 
	 * @return
	 */
	public static Response makeOKResponse() {
		return makeOKResponse(ResponseConstants.MSG_SUCCESS, null);
	}

	/**
	 * Will set the response to 200 and message to the input
	 * 
	 * @param msg
	 * @return
	 */
	public static Response makeOKResponse(String msg) {
		return makeOKResponse(msg, null);
	}

	public static Response makeOKResponse(Object data) {
		return makeOKResponse(ResponseConstants.MSG_SUCCESS, data);
	}

	/**
	 * HTTP 400 response.
	 * 
	 * @param msg
	 *            string message, recommended.
	 * @param data
	 *            converted to JSON data, not required.
	 * @return the response
	 */
	public static Response makeExceptionResponse(String msg, Object data) {
		S_Response resp = new S_Response(ResponseConstants.HTTP_ERROR, msg, data);
		return resp.toResponse();
	}

	/**
	 * Will set the response to 400 with the message to the input
	 * 
	 * @param msg
	 * @return
	 */
	public static Response makeExceptionResponse(String msg) {
		return makeExceptionResponse(msg, null);
	}

	/**
	 * HTTP 412 response.
	 * 
	 * @param msg
	 *            string message, recommended.
	 * @param data
	 *            converted to JSON data, not required.
	 * @return the response
	 */
	public static Response makeBadParamResponse(String msg, Object data) {
		S_Response resp = new S_Response(ResponseConstants.HTTP_BAD_PARAM, msg, data);
		return resp.toResponse();
	}

	/**
	 * Will set the response code to 412 and the message to the input
	 * 
	 * @param msg
	 * @return
	 */
	public static Response makeBadParamResponse(String msg) {
		return makeBadParamResponse(msg, null);
	}

	/**
	 * Serves a file.
	 * 
	 * @param filepath
	 *            the filepath.
	 * @return the response
	 * @throws Exception
	 *             the exception
	 */
	public static Response makeBinaryResponse(StreamingOutput out) throws Exception {

		return Response.status(ResponseConstants.HTTP_OK).entity(out).build();
	}

	/**
	 * Make response
	 * 
	 * @param statusCode
	 * @param msg
	 * @param data
	 *            converted to JSON data, not required.
	 * @return Response
	 * @throws Exception
	 */
	public static Response makeResponse(int statusCode, String msg, Object data) throws Exception {
		S_Response resp = new S_Response(statusCode, msg, data);
		return resp.toResponse();
	}
}

