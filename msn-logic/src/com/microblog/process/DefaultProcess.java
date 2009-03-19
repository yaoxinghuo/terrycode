package com.microblog.process;

import com.microblog.util.Logs;

public class DefaultProcess extends ProcessBase {

	public DefaultProcess(String account) throws Exception {
		super();
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
				friendTextMessageReceived(command);
			} catch (Exception e) {
				Logs.getLogger().error(
						"Error process command:" + command.toString()
								+ "\texception:" + e.getMessage());
			}
		default:
		}
	}

	@Override
	public void friendTextMessageReceived(Command command) throws Exception {
		String email = command.getEmail();
		String reply = "本機器人" + command.getAccount() + "暫未正式啟用，敬請期待！";
		Logs.getLogger().info(
				"Call webservice to send text(" + reply + ") to " + email);
		if (!wsActionService.sendText(passport, passcode, email, reply))
			Logs.getLogger().error(
					"Unable to send text to " + email + " via webservice");
	}

	@Override
	protected void friendDisplayPicChanged(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void friendNicknameChanged(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void friendPersonalMessageChanged(Command command)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void friendKnockOn(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void someoneAddMe(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
