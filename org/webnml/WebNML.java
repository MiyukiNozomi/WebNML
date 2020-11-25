package org.webnml;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

public class WebNML {

	static String file;
	static String current;
	static StringBuilder result;

	//classes
	private ServerIO serverIO;
	private IO io;
	ScriptEngine engine;
	
	public WebNML() {
		engine = new ScriptEngineManager().getEngineByName("javascript");
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.remove("document");
		bindings.remove("window");
		bindings.remove("console");
		bindings.remove("Console");
		bindings.remove("alert");
		bindings.remove("exit");
		bindings.remove("JSON");
		
		serverIO = new ServerIO(engine);
		io = new IO(engine);
		
		
		bindings.put("io",io);
		bindings.put("server", serverIO);
	}
	
	public String start(String page) {
		try {
			file = new String(Files.readAllBytes(Paths.get(page)));

			String[] sources = StringUtils.substringsBetween(file,
					"<nmlscript>", "</nmlscript>");
		
			if(sources == null)
				return file;
			
			result = new StringBuilder();

			for (String str : sources) {
				current = "<nmlscript>" + str + "</nmlscript>";
				engine.eval(str);
				file = file.replace(current, result.toString());
				result = new StringBuilder();
			}

			return file;
		} catch (Exception e) {   
			e.printStackTrace();
			return ERROR_PAGE_UP + e.getMessage() + ERROR_PAGE_DOWN;
		}
	}

	public static final String ERROR_PAGE_UP = "<html><head><title>WebNML Server Error</title></head><body><div class=\"header\"><a style=\"font-size: 30; color: #2F004D;\">WebNML</a><div class=\"header-bottom\" style=\"float: bottom; color: #2605FA;\"><a style=\"border: 1px solid #ACD8F0;\">Github Page</a> <a style=\"border: 1px solid #ACD8F0;\">About</a></div></div><div class=\"content\"><h2>Broken Page</h2><p>The WebNML has been detected that this is a invalid WebNML HTTP File.</p><p>Reason: ";
	public static final String ERROR_PAGE_DOWN = "</p></div><style>body {margin: 0;}body a {text-decoration: none;}.content {padding-left: 10px;}.header {overflow: hidden;background-color: #EBF4FB;padding: 20px 10px;border: 1px solid #ACD8F0;}.header_main {padding-left: 12px;}</style></body></html>";
}
