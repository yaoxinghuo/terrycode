package com.microblog.logic;

import com.microblog.process.Command;
import com.microblog.process.Process;

public class ForumProcess extends Process {

	public ForumProcess() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isAdmin(String friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isSuperAdmin(String friend) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void process(Command command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void textMessage(Command command) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
