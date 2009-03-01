package com.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Socket Server/Client通讯测试
 */
public class Client {
	private static final int SERVER_PORT = 10000;

	public Client() throws IOException {

		try {

			new CreateClientThread();

		} catch (IOException e) {
		} finally {

		}
	}

	// --- CreateServerThread
	class CreateClientThread extends Thread {
		private Socket server;
		@SuppressWarnings("unused")
		private BufferedReader in;
		private PrintWriter out;

		public CreateClientThread() throws IOException {
			server = new Socket("127.0.0.1", SERVER_PORT);

			in = new BufferedReader(new InputStreamReader(server
					.getInputStream(), "GB2312"));
			out = new PrintWriter(server.getOutputStream(), true);
			start();
		}

		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String line;

				while (true) {
					line = br.readLine();
					if (line == null || line.equals(""))
						continue;
					System.out.println("Sending message...\t" + line);
					out.println(line);
					if (line.equals("bye"))
						break;
				}
				out.println("--- See you, bye! ---");
				server.close();
			} catch (IOException e) {
			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Client();
	}
}
