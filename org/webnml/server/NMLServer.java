package org.webnml.server;

import org.webnml.WebNML;
import org.webnml.server.ConfigManager.Config;

public class NMLServer {
	
	public static final String CRLF = "\n\r";
	private static ConfigManager configManager;
	private Config config;
	private WebNML nml;
	private static String mainPage;
	private HTMLServer httpServer;
	
	public NMLServer() {
		configManager = new ConfigManager();
		config = configManager.getCurrentConfig();
		
		nml = new WebNML();
		mainPage = nml.start("indlex.html");
		httpServer = new HTMLServer(config.getServerIP(),config.getPort(),config.getWebroot());
	}
	
	public void start(){

		System.out.println("Starting Main Server...");
		httpServer.start();
	}
	
	public void stop(){
		httpServer.prematureStop();
	}
	
	public void resetServer(){
		httpServer = new HTMLServer(config.getServerIP(),config.getPort(),config.getWebroot());
	}

	public static void main(String[] args) {
		System.setOut(new CustomSTDOUT(System.out, "INFO"));
		System.setErr(new CustomSTDOUT(System.err, "ERROR"));

		System.out.println("Custom STDOUT Started.");

		new NMLServer().start();
	}
	
	public static String getPage(){
		return mainPage;
	}
}
