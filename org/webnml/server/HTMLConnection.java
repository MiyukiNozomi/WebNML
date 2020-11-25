package org.webnml.server;

import static org.webnml.server.NMLServer.CRLF;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTMLConnection extends Thread {

	private Socket socket;

	public HTMLConnection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		OutputStream out = null;
		InputStream in = null;
		try {

			in = socket.getInputStream();
			out = socket.getOutputStream();

			String html = NMLServer.getPage();

			String response = "HTTP/1.1 200 OK" + CRLF + "Content-Length: "
					+ html.getBytes().length + CRLF + CRLF + html + CRLF + CRLF;

			out.write(response.getBytes());
		} catch (Exception e) {
			System.err.println(socket.getInetAddress().toString() + " Disconnected.");
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			try {
				System.out.println(socket.getInetAddress().toString() + "'s Connection has been Disabled.");
				socket.close();
			} catch (Exception e) {
			}
		}
	}

}
