package org.webnml.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.webnml.server.CustomSTDOUT;

public class HTTPParser {

	private CustomSTDOUT out = new CustomSTDOUT(System.out, "HTTPParser");
	private CustomSTDOUT error = new CustomSTDOUT(System.err, "HTTPParser");

	private static final int SP = 0x20; //32
	private static final int CR = 0x0D; //13
	private static final int LF = 0x0A; //10
	
	public HTTPRequest parseRequest(InputStream inputStream) throws IOException, HTTPParserException {
		InputStreamReader reader = new InputStreamReader(inputStream,
				StandardCharsets.US_ASCII);

		HTTPRequest request = new HTTPRequest();
		
		parseLine(reader, request);
		parseHeaders(reader, request);
		parseBody(reader, request);
		
		return request;
	}

	private void parseLine(InputStreamReader reader, HTTPRequest request) throws IOException, HTTPParserException {
		int _byte;
		StringBuilder processBuffer = new StringBuilder();
		
		boolean methodParsed = false;
		boolean requestTatgetParsed = false;
		
		while((_byte = reader.read()) >= 0){
			if(_byte == CR){
				_byte = reader.read();
				if(_byte == LF){
					out.println("Resquest line VERSION to process: {" + processBuffer.toString() + "}");
					if(!methodParsed || !requestTatgetParsed){
						throw new HTTPParserException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
					}
					return;
				}
			}
			
			if(_byte == SP){
				if(!methodParsed){
					out.println("Resquest line METHOD to process: {" + processBuffer.toString() + "}");	
					request.setMethod(processBuffer.toString());
					methodParsed = true;
				}else if(!requestTatgetParsed){
					out.println("Resquest line REQ TARGET to process: {" + processBuffer.toString() + "}");	
					requestTatgetParsed = true;
				}else{
					throw new HTTPParserException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
				}
				processBuffer.delete(0,processBuffer.length());
			}else{
				processBuffer.append((char) _byte);
				if(!methodParsed){
					if(processBuffer.length() > HTTPMethod.MAX_LENGTH){
						throw new HTTPParserException(HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
					}
				}
			}
		}
	}

	private void parseHeaders(InputStreamReader reader, HTTPRequest request) {

	}

	private void parseBody(InputStreamReader reader, HTTPRequest request) {

	}
}
