package com.microblog.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import com.microblog.process.Command;
import com.microblog.process.CommandParserException;
import com.microblog.process.Commands;
import com.microblog.util.Logs;

public class SocketObservable extends Observable implements Runnable {

	private class Cmd {
		int len;
		Commands name;
		String account;
		String email;
		String body;

		Command command;

		public Cmd(final String line) throws CommandParserException, IOException {
			String[] parts = line.split(" ");
			if (parts.length < 3)
				throw new CommandParserException("Syntax Error:" + line);
			try {
				name = Commands.valueOf(parts[0]);
			} catch (Exception e) {
				throw new CommandParserException("Unrecognized command:"
						+ parts[0]);
			}
			account = parts[1];
			email = parts[2];
			try {
				len = Integer.parseInt(parts[3]);
			} catch (Exception e) {
				throw new CommandParserException("Unable to parser number:"
						+ parts[3]);
			}
			final byte[] bb = new byte[len];
			try {
				in.read(bb, 0, len);
				body = new String(bb, "UTF-8");
				command = new Command(name, account, email, len, body);
			} catch (IOException e) {
				throw new IOException("Unable to read extra msg");
			}
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(name.toString()).append(" ");
			sb.append(account).append(" ");
			sb.append(email).append(" ");
			sb.append(len).append("\n");
			if (len != 0)
				sb.append(body);
			return sb.toString();
		}
	}

	private String host;
	private int port;
	private String passport;
	private String passcode;

	private Socket server;

	private PrintWriter out;
	private final ByteArrayOutputStream inbuf = new ByteArrayOutputStream();
	private InputStream in;

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
		out.print(commandString);
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (!isConnected) {
					continue;
				}
				if (readLine().trim().equalsIgnoreCase(OK)) {
					isReady = true;
					Logs
							.getLogger()
							.info(
									"Receive 'ok' message from server.Ready for incoming comands...");
					while (true) {
						Cmd cmd;
						try {
							cmd = new Cmd(readLine());
							Logs.getLogger().info(
									"Receive command from server:\t"
											+ cmd.toString());
							fireChanged(cmd.command);
						} catch (CommandParserException e) {
							Logs.getLogger().error(
									"Error parse command.exception:"
											+ e.getMessage());
						}
					}
				}
			}
		} catch (IOException e) {
			isConnected = false;
			isReady = false;
			Logs.getLogger().error(
					"Socket connection error:\t" + e.getMessage());
			setConnectionCloseAndReconnect();
		}

	}

	private String readLine() throws IOException {
		inbuf.reset();
		while (true) {
			final int v = in.read();
			if (v == -1) {
				return null;
			}
			if (v == 13) {
				continue;
			}
			if (v == 10) {
				break;
			}
			inbuf.write(v);
		}
		return new String(inbuf.toByteArray(), "UTF-8");
	}

	public void fireChanged(Command command) {
		setChanged();
		notifyObservers(command);
	}

	private void setConnectionCloseAndReconnect() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (server != null && !server.isClosed())
				server.close();
		} catch (IOException e) {
		}
		server = null;
		in = null;
		out = null;

		connect();
	}

	private void connect() {
		try {
			server = new Socket(host, port);
			in = server.getInputStream();
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
