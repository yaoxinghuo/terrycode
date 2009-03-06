package com.microblog.process;

import java.util.Hashtable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.microblog.data.service.intf.IServiceService;
import com.microblog.util.Logs;
import com.microblog.util.Settings;

public class ProcessControl {
	private static Hashtable<String, Robot> robots = new Hashtable<String, Robot>();
	private IServiceService serviceService;
	private Settings settings;

	public ProcessControl() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"applicationContext.xml");
		serviceService = (IServiceService) ctx.getBean("serviceService");
		settings = Settings.getInstance();
	}

	public void doUpdate(Command command) {
		String account = command.getAccount();
		Robot robot = robots.get(account);
		if (robot == null) {
			int type = serviceService.imGetRobotTypeByAccount(account);
			ProcessBase process;
			try {
				switch (type) {
				case 2:
					process = new ForumProcess(account);
					break;
				case 1:
					process = new TimelineProcess(account);
					break;
				default:
					process = new DefaultProcess(account);
					Logs
							.getLogger()
							.error(
									"Robot account:"
											+ account
											+ " not registered in DB. call default process");
				}
				robot = new Robot(account, settings.getSocketPassport(),
						settings.getSocketPasscode());
				robot.setProcess(process);
				robots.put(account, robot);
				Logs.getLogger().info(
						"Robot instance null, create a new one for:\t"
								+ account);
			} catch (Exception e) {
				Logs.getLogger().error(
						"Could not to create process instance.Exception:\t"
								+ e.getMessage());
			}

		}
		if (robot != null) {
			Logs.getLogger().info(
					"Call robot daemon(" + account
							+ ") to process the command...");
			robot.getProcess().process(command);
		}
	}

	public void setServiceService(IServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public IServiceService getServiceService() {
		return serviceService;
	}
}
