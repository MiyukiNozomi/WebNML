package org.webnml.http;

public class HTTPParserException extends Exception {

	private static final long serialVersionUID = 1L;
	private HTTPStatusCode error_code;
	
	public HTTPParserException(HTTPStatusCode code) {
		super(code.MESSAGE);
		this.error_code = code;
	}
	
	public HTTPStatusCode getErrorCode() {
		return error_code;
	}
}
