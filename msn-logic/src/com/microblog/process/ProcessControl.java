package com.microblog.process;

import java.util.Hashtable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.microblog.data.model.Robot;
import com.microblog.data.service.intf.IServiceService;
import com.microblog.util.Logs;

public class ProcessControl {
	private static Hashtable<String, RobotWrapper> robots = new Hashtable<String, RobotWrapper>();
	private IServiceService serviceService;

	public ProcessControl() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"applicationContext.xml");
		serviceService = (IServiceService) ctx.getBean("serviceService");
	}

	public void doUpdate(Command command) {
		String account = command.getAccount();
		RobotWrapper robotWrapper = robots.get(account);
		if (robotWrapper == null) {
			Robot robot = serviceService.imGetRobotByAccount(account);
			if (robot == null) {
				Logs.getLogger().error(
						"Robot account:" + account + " not registered in DB.");
				return;
			}
			ProcessBase process;
			try {
				switch (robot.getType()) {
				case 2:
					process = new ForumProcess(robot.getPassport(), robot
							.getPasscode(), account);
					break;
				case 1:
					process = new TimelineProcess(robot.getPassport(), robot
							.getPasscode(), account);
					break;
				default:
					process = new DefaultProcess(account);
					Logs
							.getLogger()
							.error(
									"Robot account:"
											+ account
											+ " type not correct in DB. call default process");
				}
				robotWrapper = new RobotWrapper(robot.getPassport(), robot
						.getPasscode(), account);
				robotWrapper.setProcess(process);
				robots.put(account, robotWrapper);
				Logs.getLogger().info(
						"Robot instance null, create a new one for:\t"
								+ account);
			} catch (Exception e) {
				Logs.getLogger().error(
						"Could not to create process instance.Exception:\t"
								+ e.getMessage());
			}

		}
		if (robotWrapper != null) {
			Logs.getLogger().info(
					"Call robot daemon(" + account
							+ ") to process the command...");
			robotWrapper.getProcess().process(command);
		}
	}

	public void setServiceService(IServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public IServiceService getServiceService() {
		return serviceService;
	}
}
