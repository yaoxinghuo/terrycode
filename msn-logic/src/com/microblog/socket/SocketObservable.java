package com.microblog.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Observable;

import com.microblog.process.Command;
import com.microblog.process.CommandParserException;
import com.microblog.process.Commands;
import com.microblog.util.Logs;

public class SocketObservable extends Observable implements Runnable {

	private static final String QNG = "QNG";
	private static final String PNG = "PNG";
	private long lastReceiveQNGTime = 0;
	private static final int MAX_INACTIVE_TIME = 70 * 1000;

	private class Cmd {
		int len;
		Commands name;
		String account;
		String email;
		String body;

		Command command;

		public Cmd(final String line) throws CommandParserException,
				IOException {
			if (line.trim().equalsIgnoreCase(QNG)) {// Client端會定期接收到Server檢查指令
				lastReceiveQNGTime = new Date().getTime();
				name = Commands.QNG;
				out.println(PNG);// Client收到指定令須於30秒內回送回應指令, 以確保連線有效
				Logs.getLogger().info(
						"Receive QNG from server.echo PNG to server...");
				return;
			}
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

		new Thread() {
			/*
			 * (from Robert's Email)
			 * 
			 * 目前觀察機房firewall發現如果session接超過一定時間沒有動作firwall會把 session 踢掉
			 * 如此會造成各位ｂ端程式接上後一段時間後離線
			 * 
			 * 我這邊會試著調整（但是目前發現是最多八萬秒，似乎無法調整）
			 * 
			 * 同時為了避免其他網路層的未知因素斷線請各位開發時務必要多做一件事
			 * 
			 * 就請記下你收到最後一你收到確認的tag的時間如果超過70秒沒收到
			 */
			@Override
			public void run() {
				while (true) {
					if (isConnected
							&& new Date().getTime() - lastReceiveQNGTime > MAX_INACTIVE_TIME) {
						Logs
								.getLogger()
								.info(
										"Havent receive 'QNG' from server for "
												+ MAX_INACTIVE_TIME
												+ "ms. Disconnect and reconnect now...");
						setConnectionCloseAndReconnect();
					}
					try {
						Thread.sleep(MAX_INACTIVE_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.run();

		connect();
	}

	@Deprecated
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
		while (true) {
			try {
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
						try {
							Cmd cmd = new Cmd(readLine());
							if (cmd.name == Commands.QNG)
								continue;
							Logs.getLogger().info(
									"Receive command from server:\t"
											+ cmd.toString());
							fireChanged(cmd.command);
						} catch (CommandParserException e) {
							Logs.getLogger().error(
									"Error parse command.CommandParserException:"
											+ e.getMessage());
						}
					}
				}
			} catch (Exception e) {
				isConnected = false;
				isReady = false;
				Logs.getLogger().error(
						"Socket connection error:\t" + e.getMessage());
				setConnectionCloseAndReconnect();
			}
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
		isConnected = false;
		isReady = false;
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
