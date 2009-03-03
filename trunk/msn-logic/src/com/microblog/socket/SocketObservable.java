package com.microblog.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import com.microblog.process.Command;
import com.microblog.util.Logs;

public class SocketObservable extends Observable implements Runnable {
	private String host;
	private int port;
	private String passport;
	private String passcode;

	private Socket server;
	private BufferedReader br;
	private PrintWriter out;

	private static final String OK = "ok";

	private boolean isConnected = false;
	private boolean isReady = false;

	public SocketObservable(String host, int port, String passport,
			String passcode) {
		this.host = host;
		this.port = port;
		this.passcode = passcode;
		this.passport = passport;

		connect();
	}

	public void sendCommand(Command command) {
		if (!isReady)
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		String commandString = command.toString();
		Logs.getLogger().info(
				"Client send command to server:\t" + commandString);
		out.println(commandString);
	}

	@Override
	public void run() {
		try {
			String line;
			while (true) {
				if (!isConnected) {
					continue;
				}
				line = br.readLine();
				if (line == null || line.trim().equals("")) {
					continue;
				}
				Logs.getLogger().debug(
						"Receive socket message from server:\t" + line);
				if (line.trim().equalsIgnoreCase(OK)) {
					isReady = true;
					Logs
							.getLogger()
							.info(
									"Receive ok message from server.Ready for incoming comands...");
					continue;
				}
				if (!isReady) {
					continue;
				}
				Logs.getLogger().info(
						"Receive command from server:\t" + line
								+ "\tParse it...");
				fireChanged(line);
			}
		} catch (IOException e) {
			isConnected = false;
			isReady = false;
			Logs.getLogger().error(
					"Socket connection error:\t" + e.getMessage());
			setConnectionCloseAndReconnect();
		}

	}

	public void fireChanged(String line) {
		setChanged();
		notifyObservers(new SocketEventObject(new String(line)));
	}

	private void setConnectionCloseAndReconnect() {
		try {
			if (br != null)
				br.close();
			if (out != null)
				out.close();
			if (server != null)
				server.close();
		} catch (IOException e) {
		}
		server = null;
		br = null;
		out = null;

		connect();
	}

	private void connect() {
		try {
			server = new Socket(host, port);

			br = new BufferedReader(new InputStreamReader(server
					.getInputStream(), "UTF-8"));
			out = new PrintWriter(server.getOutputStream(), true);
			isConnected = true;
			Logs.getLogger().info("Socket connected to:\t" + host + ":" + port);
			out.println(passport);
			out.println(passcode);
			Logs.getLogger().info(
					"Send passport&passcode to:\t" + host + ":" + port);
		} catch (Exception e) {
			Logs.getLogger().error(
					"Socket connection disconnected, exception:\t"
							+ e.getMessage()
							+ "\tSystem will reconnect in 1 minute...");
			try {
				Thread.sleep(60000);// 一分钟后重新尝试连接
			} catch (InterruptedException e1) {
			}
			setConnectionCloseAndReconnect();
		}
	}
}
