package com.microblog.logic;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import com.microblog.process.Command;
import com.microblog.process.Robot;
import com.microblog.socket.SocketObservable;
import com.microblog.util.Logs;

public class Main {

	private static Hashtable<String, Robot> robots = new Hashtable<String, Robot>();

	private static Fuc fuc = Fuc.timeline;

	public static void main(final String[] args) throws Exception {
		String usage = "Usage:java -jar appname.jar timeline|forum";
		if (args.length != 1) {
			System.out.println(usage);
			return;
		}

		try {
			fuc = Fuc.valueOf(args[1]);
			Logs.getLogger().info(
					"System will run in " + fuc.toString() + " model.\t");
		} catch (Exception e1) {
			Logs
					.getLogger()
					.info(
							"Unrecoginzed arg entered. System will run in timeline model.");
		}

		final Settings settings = Settings.getInstance();
		if (settings == null)
			throw new Exception("Settings null");
		final SocketObservable client = new SocketObservable(settings
				.getSocketHost(), settings.getSocketPort(), settings
				.getSocketPassport(), settings.getSocketPasscode());
		client.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				Command command = (Command) arg;
				Robot robot = robots.get(command.getAccount());
				if (robot == null) {
					com.microblog.process.Process process;
					try {
						switch (fuc) {
						case forum:
							process = new ForumProcess();
							break;
						case timeline:
							process = new TimelineProcess();
							break;
						default:
							process = new TimelineProcess();
						}
						robot = new Robot(command.getAccount(), settings
								.getSocketPassport(), settings
								.getSocketPasscode());
						robot.setProcess(process);
						robots.put(command.getAccount(), robot);
						Logs.getLogger().info(
								"Robot instance null, create a new one for:\t"
										+ command.getAccount());
					} catch (Exception e) {
						Logs.getLogger().error(
								"Could not to create process instance.Exception:\t"
										+ e.getMessage());
					}

				}
				if (robot != null) {
					Logs.getLogger().info(
							"Call robot daemon(" + command.getAccount()
									+ ") to process the command...");
					robot.getProcess().process(command);
				}
			}
		});
		Thread thread = new Thread(client);
		thread.start();
	}
}
