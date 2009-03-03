package com.microblog.logic;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import com.microblog.process.Command;
import com.microblog.process.Robot;
import com.microblog.socket.SocketEventObject;
import com.microblog.socket.SocketObservable;
import com.microblog.util.Logs;

public class Main {

	private static Hashtable<String, Robot> robots = new Hashtable<String, Robot>();

	private static Command command = null;

	public static void main(final String[] args) throws Exception {
		final Settings settings = Settings.getInstance();
		if (settings == null)
			throw new Exception("Settings null");
		final SocketObservable client = new SocketObservable(settings
				.getSocketHost(), settings.getSocketPort(), settings
				.getSocketPassport(), settings.getSocketPasscode());
		client.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				String commandMsg = ((SocketEventObject) arg).toString();
				if (command == null || command.isNextCommandStart())
					try {
						command = new Command(commandMsg);
					} catch (Exception e1) {
						Logs.getLogger().error(
								"Error parser socket command from server:\t"
										+ commandMsg + "\tError message:\t"
										+ e1.getMessage());
					}
				else
					command.appendCommandMsg(commandMsg);
				if (command == null || !command.isNextCommandStart())
					return;// 如果这条消息还没有结束，那么先不要处理，等待下一条Socket消息合并过来。
				Robot robot = robots.get(command.getRobotId());
				if (robot == null) {
					TimelineProcess process = null;
					try {
						process = new TimelineProcess();
						robot = new Robot(command.getRobotId(), settings
								.getSocketPassport(), settings
								.getSocketPasscode());
						robot.setProcess(process);
						robots.put(command.getRobotId(), robot);
						Logs.getLogger().info(
								"Robot instance null, create a new one for:\t"
										+ command.getRobotId());
					} catch (Exception e) {
						Logs.getLogger().error(
								"Could not to create process instance.Exception:\t"
										+ e.getMessage());
						e.printStackTrace();
					}
				}
				if (robot != null) {
					Logs.getLogger().info(
							"Call robot daemon(" + command.getRobotId()
									+ ") to process the command...");
					robot.getProcess().process(command);
				}
			}
		});
		Thread thread = new Thread(client);
		thread.start();
	}
}
