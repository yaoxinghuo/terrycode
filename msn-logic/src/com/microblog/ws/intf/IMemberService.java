package com.microblog.ws.intf;

import com.microblog.ws.model.MemberStatusWrapper;

public interface IMemberService {

	public void init(String wsUrl, String passport, String passcode)
			throws Exception;

	public int acceptFriend(String account, String email) throws Exception;

	public int addFriend(String account, String email, int type)
			throws Exception;

	public boolean allowFriend(String account, String email) throws Exception;

	public boolean blockFriend(String account, String email) throws Exception;

	public int removeAndBlockFriend(String account, String email)
			throws Exception;

	public int removeFriend(String account, String email) throws Exception;

	public String[] friendList(String account) throws Exception;

	public String[] pendingList(String account) throws Exception;

	public MemberStatusWrapper friendStatus(String account, String email)
			throws Exception;
}
