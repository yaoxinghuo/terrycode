package com.microblog.data.service.intf;

import java.util.Date;
import java.util.Hashtable;

public interface IMessageService {
	public String imSaveMessage(String accountid, String content, String image,
			String groupid, String friendid, int purview);

	public boolean imUpdateMessageContent(String accountid, String messageid,
			String content);

	public Hashtable<String, String> imGetMessageLists(Date date, int purview,
			String groupid, String accountid, int start, int limit);

	public Hashtable<String, String> imGetForumMessageLists(Date date,
			String forumid, String accountid, int start, int limit);

	public Hashtable<String, String> imGetUserPublicMessageLists(Date date,
			String accountid, String friendid, int start, int limit);

	public Hashtable<String, String> imGetOwnSubscribeMessageLists(Date date,
			String accountid, String subscribeid, int start, int limit);

	public Hashtable<String, String> imGetOwnTraceSubscribeMessageList(
			Date date, String accountid, int start, int limit);

	public Hashtable<String, String> imGetOwnMessageCategory(String accountid,
			int type);

	public String imGetMessageDetailInfoById(String messageid, String userid);
}
