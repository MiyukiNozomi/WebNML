package org.webnml;

import static org.webnml.WebNML.result;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;

public class ServerIO extends Bind {
	
	public ServerIO(ScriptEngine engine) {
		super(engine);
	}
	
	public String fread(String file){
		try{
			return new String(Files.readAllBytes(Paths.get(file)));
		}catch(Exception e){
			result.append("<h2>[WebNML] ERROR</h2>: Unable to read file: " + file + " check the console for details.");
			e.printStackTrace();
			return "";
		}
	}
	
	public void fwrite(String file,String data){
		try{
			Files.write(Paths.get(file),data.getBytes());
		}catch(Exception e){
			result.append("<h2>[WebNML] ERROR</h2>: Unable to write file: " + file + " check the console for details.");
			e.printStackTrace();
		}
	}
	
	public void println(String msg) {
		if (msg == null) {
			result.append("<h2>[WebNML] WARN</h2>: msg == null");
			return;
		}
		result.append(msg + "\n\r");
	}

	public void println(char msg) {
		result.append(msg + "\n\r");
	}

	public void println(double msg) {
		result.append(msg + "\n\r");
	}

	public void println(float msg) {
		result.append(msg + "\n\r");
	}

	public void println(boolean msg) {
		result.append(msg + "\n\r");
	}

	public void println(char[] msg) {
		result.append(new String(msg) + "\n\r");
	}

	public void println(long msg) {
		result.append(msg + "\n\r");
	}

	public void println(int msg) {
		result.append(msg + "\n");
	}

	public void println(Object msg) {
		if (msg == null) {
			result.append("<h2>[WebNML] WARN</h2>: msg == null");
			return;
		}
		result.append(msg + "\n");
	}
}
