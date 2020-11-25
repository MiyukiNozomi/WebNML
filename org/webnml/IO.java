package org.webnml;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import static org.webnml.WebNML.result;

public class IO extends Bind {
	
	public IO(ScriptEngine engine) {
		super(engine);
	}
	
	public void fadd(String path){
		try{
			result.append(new String(Files.readAllBytes(Paths.get(path))));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void include(String path){
		try{
			engine.eval(new String(Files.readAllBytes(Paths.get(path))));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
