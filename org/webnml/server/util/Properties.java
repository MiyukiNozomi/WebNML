package org.webnml.server.util;

import java.util.ResourceBundle;

public class Properties {
	
	private ResourceBundle bundle;
	
	public Properties(String filename) {
		 bundle = ResourceBundle.getBundle("config." + filename);
	}
	
	public boolean contains(String key){
		return bundle.containsKey(key);
	}
	
	public String getUTF(String key){
		return bundle.getString(key);
	}
	
	public int getInteger(String key){
		return Integer.parseInt(bundle.getString(key));
	}	
}
