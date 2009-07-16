package com.test.socket;

import java.io.*;
import java.net.*;

/*
 * Socket Server/Client通讯测试
 */
public class Server extends ServerSocket {
	private static final int SERVER_PORT = 6035;

	public Server() throws IOException {
		super(SERVER_PORT);

		try {
			System.out.println("Ready for connection...");
			while (true) {
				Socket socket = accept();
				new CreateServerThread(socket);
			}
		} catch (IOException e) {
		} finally {
			close();
		}
	}

	// --- CreateServerThread
	class CreateServerThread extends Thread {
		private Socket client;
		private BufferedReader in;
		private PrintWriter out;

		public CreateServerThread(Socket s) throws IOException {
			client = s;

			in = new BufferedReader(new InputStreamReader(client
					.getInputStream(), "GB2312"));
			out = new PrintWriter(client.getOutputStream(), true);
			//out.println("--- Welcome ---");
			start();
		}

		@Override
		public void run() {
			try {
				String line = in.readLine();

				while (!line.equals("bye")) {
					System.out.println("Comming message...\t"+line);
					String msg = createMessage(line);
					out.println(msg);
					line = in.readLine();
				}
				out.println("--- See you, bye! ---");
				client.close();
			} catch (IOException e) {
			}
		}

		private String createMessage(String line) {
			return line;
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}