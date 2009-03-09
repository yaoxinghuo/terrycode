package com.microblog.ws.intf;

import com.microblog.ws.model.MemberStatusWrapper;

public interface IMemberService {
	public int acceptFriend(String passport, String passcode, String account,
			String email) throws Exception;

	public int addFriend(String passport, String passcode, String account,
			String email, int type) throws Exception;

	public boolean allowFriend(String passport, String passcode,
			String account, String email) throws Exception;

	public boolean blockFriend(String passport, String passcode,
			String account, String email) throws Exception;

	public int removeAndBlockFriend(String passport, String passcode,
			String account, String email) throws Exception;

	public int removeFriend(String passport, String passcode, String account,
			String email) throws Exception;

	public String[] friendList(String passport, String passcode, String account)
			throws Exception;

	public String[] pendingList(String passport, String passcode, String account)
			throws Exception;

	public MemberStatusWrapper friendStatus(String passport, String passcode,
			String account, String email) throws Exception;
}
