package org.webnml.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTMLServer extends Thread {

	private int port;
	private String webroot, serverIP;
	private ServerSocket server;
	private InetSocketAddress adress;

	public HTMLServer(String serverIP, int port, String webroot) {
		super.setName("HTTPServer");
		this.port = port;
		this.webroot = webroot;
		this.serverIP = serverIP;
	}

	public void startup() {
		try {
			adress = new InetSocketAddress(serverIP, port);
			System.out.println("Creating ServerSocket...");
			server = new ServerSocket();
			System.out.println("Binding Adress: " + adress.toString());
			server.bind(adress);
			System.out.println("Web Root Folder: " + webroot);
		} catch (Exception e) {
			System.out
					.println("An Exception happened while server was running.");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		startup();
		try {
			while (server.isBound() && !server.isClosed()) {
				Socket socket = server.accept();

				System.out.println("Connection From: "
						+ socket.getInetAddress().toString());

				HTMLConnection CONNECTION = new HTMLConnection(socket);
				CONNECTION.start();

			}
		} catch (Exception e) {
			System.out
					.println("Server Crashed!");
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void prematureStop(){
		try{
			server.close();
			join();
		}catch(Exception e){
			e.printStackTrace();
		}
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
}