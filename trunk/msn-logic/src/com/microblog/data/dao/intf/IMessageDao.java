package com.microblog.data.dao.intf;

import java.util.Date;
import java.util.List;

import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;
import com.microblog.data.model.Message;

public interface IMessageDao {
	public Message getMessageById(String id);

	public long getMessagesTotalCountByAccount(Account account);

	public long getMessagesTotalCountByDayAccount(Account account, Date uDate);

	public List<Message> getPublicMessagesByDayAndAccounts(Date date,
			int field, String accountid, List<Friend> friends, int start, int limit);

	public List<Message> getMessagesByAccount(Account account);

	public List<Message> getCommunityMessagesByDate(Date date, int field, int start, int limit);

	public List<Message> getOwnMessagesByDate(Date date, int calendarField,
			String accountid, int start, int limit);

	public List<Message> getOwnSubscribeMessagesByDate(Date date,
			int calendarField, String accountid, String subscribeid, int start ,int limit);

	public List<Message> getForumMessagesByDate(Date date, int calendarFiend,
			String forumid, int start, int limit);

	public List<Message> getOurMessagesByDate(Date date, boolean forum,
			int calendarField, int start , int limit);

	public boolean saveMessage(Message message);

	public boolean deleteMessage(Message message);

	public List<Message> getNewestMessages(int limit);
}
