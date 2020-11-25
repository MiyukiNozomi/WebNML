package org.webnml.server;

import org.webnml.server.util.Properties;

public class ConfigManager {

	private Config currentConfig;

	public void load() {
		Properties properties = new Properties("server");

		currentConfig = new Config(properties.getInteger("port"),properties.getUTF("serverip"),
				properties.getUTF("webroot"));
	}

	public Config getCurrentConfig() {
		if (currentConfig == null)
			load();
		return currentConfig;
	}

	public static class Config {

		private int port;
		private String serverIP, webroot;

		public Config(int port, String serverIP, String webroot) {
			this.port = port;
			this.webroot = webroot;
			this.serverIP = serverIP;
		}

		public int getPort() {
			return port;
		}

		public String getWebroot() {
			return webroot;
		}

		public String getServerIP() {
			return serverIP;
		}

		public void setServerIP(String serverIP) {
			this.serverIP = serverIP;
		}

		public void setWebroot(String webroot) {
			this.webroot = webroot;
		}

		public void setPort(int port) {
			this.port = port;
		}

	}
}
