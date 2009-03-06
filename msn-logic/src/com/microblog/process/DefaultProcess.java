package com.microblog.process;

import com.microblog.util.Logs;
import com.microblog.util.Settings;

public class DefaultProcess extends ProcessBase {

	private Settings settings;

	public DefaultProcess(String account) throws Exception {
		super();
		settings = Settings.getInstance();
		init();
	}

	@Override
	protected void init() throws Exception {
		wsActionService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());
	}

	@Override
	protected boolean isAdmin(String friend) {
		return false;
	}

	@Override
	protected boolean isSuperAdmin(String friend) {
		return false;
	}

	@Override
	public void process(Command command) {
		Commands commandEnum = command.getName();
		switch (commandEnum) {
		case MSG:
			try {
				textMessage(command);
			} catch (Exception e) {
				Logs.getLogger().error(
						"Error process command:" + command.toString()
								+ "\texception:" + e.getMessage());
			}
		default:
		}
	}

	@Override
	public void textMessage(Command command) throws Exception {
		wsActionService.init(settings.getWsUrl(), settings.getWsPassport(),
				settings.getWsPasscode());
		String email = command.getEmail();
		String reply = "本機器人" + command.getAccount() + "暫未正式啟用，敬請期待！";
		Logs.getLogger().info(
				"Call webservice to send text(" + reply + ") to " + email);
		if (!wsActionService.sendText(email, reply))
			Logs.getLogger().error(
					"Unable to send text to " + email + " via webservice");
	}

}
