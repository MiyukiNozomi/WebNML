package org.webnml.http;

public enum HTTPMethod {
	GET,READ;
	
	public static final int MAX_LENGTH;
	
	static {
		int tempMaxLength = -1;
		
		for(HTTPMethod m : values()){
			if(m.name().length() > tempMaxLength){
				tempMaxLength = m.name().length();
			}
		}
		
		MAX_LENGTH = tempMaxLength;
	}
}
