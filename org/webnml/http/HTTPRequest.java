package org.webnml.http;

public class HTTPRequest {

	private HTTPMethod method;
	private String requestTarget;
	private String httpVersion;

	HTTPRequest() {
	}

	void setMethod(String method) throws HTTPParserException{
		for(HTTPMethod m : HTTPMethod.values()){
			if(method.equals(m.name())){
				this.method = HTTPMethod.valueOf(method);
				return;
			}
		}
		throw new HTTPParserException(HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
	}

	void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	void setRequestTarget(String requestTarget) {
		this.requestTarget = requestTarget;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public HTTPMethod getMethod() {
		return method;
	}

	public String getRequestTarget() {
		return requestTarget;
	}
}
