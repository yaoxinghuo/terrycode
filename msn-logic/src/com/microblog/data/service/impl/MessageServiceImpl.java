package com.microblog.data.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import com.microblog.data.dao.intf.IAccountDao;
import com.microblog.data.dao.intf.IFriendDao;
import com.microblog.data.dao.intf.IGroupDao;
import com.microblog.data.dao.intf.IGroupFriendDao;
import com.microblog.data.dao.intf.IHeadDao;
import com.microblog.data.dao.intf.IMessageDao;
import com.microblog.data.dao.intf.IReplyDao;
import com.microblog.data.dao.intf.ISubscribeDao;
import com.microblog.data.model.Account;
import com.microblog.data.model.Friend;
import com.microblog.data.model.Group;
import com.microblog.data.model.GroupFriend;
import com.microblog.data.model.Limit;
import com.microblog.data.model.Message;
import com.microblog.data.model.Reply;
import com.microblog.data.model.Subscribe;
import com.microblog.data.service.intf.IMessageService;
import com.microblog.util.StringUtil;

public class MessageServiceImpl implements IMessageService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public IReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public IGroupFriendDao getGroupFriendDao() {
		return groupFriendDao;
	}

	public void setGroupFriendDao(IGroupFriendDao groupFriendDao) {
		this.groupFriendDao = groupFriendDao;
	}

	public ISubscribeDao getSubscribeDao() {
		return subscribeDao;
	}

	public void setSubscribeDao(ISubscribeDao subscribeDao) {
		this.subscribeDao = subscribeDao;
	}

	public IHeadDao getHeadDao() {
		return headDao;
	}

	public void setHeadDao(IHeadDao headDao) {
		this.headDao = headDao;
	}

	public IFriendDao getFriendDao() {
		return friendDao;
	}

	public void setFriendDao(IFriendDao friendDao) {
		this.friendDao = friendDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Resource(name = "messageDao")
	private IMessageDao messageDao;
	@Resource(name = "accountDao")
	private IAccountDao accountDao;
	@Resource(name = "replyDao")
	private IReplyDao replyDao;
	@Resource(name = "groupFriendDao")
	private IGroupFriendDao groupFriendDao;
	@Resource(name = "subscribeDao")
	private ISubscribeDao subscribeDao;
	@Resource(name = "headDao")
	private IHeadDao headDao;
	@Resource(name = "friendDao")
	private IFriendDao friendDao;
	@Resource(name = "groupDao")
	private IGroupDao groupDao;

	@Override
	public String imSaveMessage(String accountid, String content, String image,
			String groupid, String friendid, int purview) {
		Account account = accountDao.getAccountById(accountid);
		Message message = new Message();
		message.setContent(content);
		message.setImage(image);
		message.setAccount(account);
		message.setAvatar(account.getAvatar());
		message.setCdate(new Date());
		message.setUdate(message.getCdate());
		message.setIm(2);
		message.setNickname(account.getNickname());
		message.setPmessage(account.getPmessage());
		message.setReadr(true);
		message.setReplys(null);

		Limit limit = new Limit();
		limit.setMessage(message);
		limit.setPurview(purview);

		if (purview == 1) {
			Group group = groupDao.getGroupById(groupid);
			if (group.getType() == 2)
				limit.setForumid(groupid);
			else {
				List<GroupFriend> gfs = group.getGroupFriends();
				for (GroupFriend gf : gfs) {
					friendid = friendid + gf.getFriendid() + ",";
				}
			}
		}

		limit.setMto(friendid);
		message.setLimit(limit);
		if (messageDao.saveMessage(message))
			return message.getId();
		else
			return null;
	}

	@Override
	public Hashtable<String, String> imGetForumMessageLists(Date date,
			String forumid, String userid, int start, int limit) {
		List<Message> messages = messageDao.getForumMessagesByDate(date, 5,
				forumid, start, limit);
		return changeMessageFromListToHashtable(messages, 1, userid);
	}

	@Override
	public Hashtable<String, String> imGetMessageLists(Date date, int purview,
			String groupid, String accountid, int start, int limit) {
		List<Message> messages = null;
		switch (purview) {
		case -1:
			messages = messageDao.getOwnMessagesByDate(date, 5, accountid,
					start, limit);
			break;
		case 1:
			messages = messageDao.getOurMessagesByDate(date, false, 5, start,
					limit);
			break;
		default:
			messages = messageDao.getCommunityMessagesByDate(date, 5, start,
					limit);
			break;
		}
		if (purview == 1) {// 群组的时间轴比较复杂。。。
			List<Message> ms = new ArrayList<Message>();
			List<GroupFriend> gfs = groupFriendDao
					.getGroupFriendsByGroupId(groupid);
			List<Group> fgroups = groupFriendDao
					.getGuestForumGroupsByFriendId(accountid);
			for (Group group : fgroups) {
				gfs.addAll(group.getGroupFriends());
			}
			HashSet<String> friends = new HashSet<String>();
			for (int i = 0; i < gfs.size(); i++) {
				friends.add(gfs.get(i).getFriendid());
			}

			outside: for (Message message : messages) {
				if (message.getAccount_id().equals(accountid)) {
					// 如果当前账户和发布者的账户一致
					for (String friendid : friends) {
						if (message.getLimit().getMto().contains(friendid)) {
							ms.add(message);
							continue outside;
						}
					}
				} else if (message.getLimit().getMto().contains(accountid)) {
					// 如果我的账户和发布者的账户不一致，那说明要删选的消息是别人发给我的
					for (String friendid : friends) {
						if (friendid.equals(message.getAccount_id())) {
							ms.add(message);
							continue outside;
						}
					}
				}
			}

			return changeMessageFromListToHashtable(ms, purview, accountid);
		}
		return changeMessageFromListToHashtable(messages, purview, accountid);
	}

	private boolean isSubscribes(Message message, List<Subscribe> subscribes) {
		for (Subscribe subscribe : subscribes) {
			if (subscribe.getType() == 1
					&& message.getAccount_id().equals(subscribe.getSubscribe()))
				return true;
			if (subscribe.getType() == 2
					&& message.getId().equals(subscribe.getSubscribe()))
				return true;
		}
		return false;
	}

	private Hashtable<String, String> changeMessageFromListToHashtable(
			List<Message> lMessages, int purview, String userid) {
		Hashtable<String, String> messages = new Hashtable<String, String>();
		for (Message message : lMessages) {
			boolean read = true;
			if (message.getAccount_id().equals(userid) && !message.isReadr())
				read = false;
			String title;
			int maxlength = read ? 40 : 32;
			switch (purview) {
			case -1:
				if (message.getIm() == 3) {// rss订阅的新闻
					int length = message.getNickname().getBytes().length;
					title = "[RSS]"
							+ StringUtil.splitString(message.getNickname(), 14)
							+ "--"
							+ StringUtil.splitString(message.getPmessage(),
									length > 18 ? maxlength - 20 : maxlength
											- length - 2);
				} else if (message.getPmessage() == null
						|| message.getPmessage().equals(""))
					title = StringUtil.splitString(message.getContent(),
							maxlength);
				else {
					int length = message.getPmessage().getBytes().length;
					title = StringUtil.splitString(message.getPmessage(), 18)
							+ "--"
							+ StringUtil.splitString(message.getContent(),
									length > 18 ? maxlength - 20 : maxlength
											- length - 2);
				}
				break;
			case 1:
				int length = message.getNickname().getBytes().length;
				title = StringUtil.splitString(message.getNickname(), 18)
						+ "--"
						+ StringUtil.splitString(message.getContent(),
								length > 18 ? maxlength - 20 : maxlength
										- length - 2);
				break;
			default:
				title = StringUtil.splitString(message.getContent(), maxlength);
				break;
			}
			if (!read)
				title = title + "[新回復]";
			messages.put(message.getId(), title);
		}
		return messages;
	}

	@Override
	public String imGetMessageDetailInfoById(String messageid, String userid) {
		Message message = messageDao.getMessageById(messageid);
		if (message == null)
			return "對不起，可能這個發佈已經被移除！";

		String tzone;
		// boolean canaddfriend = true;
		Account account = accountDao.getAccountById(userid);
		tzone = StringUtil.changeFloatTimeZoneToString(account.getTzone());

		// if (message.getAccount_id().equals(userid))
		// canaddfriend = false;
		// else {
		// Friend friend = friendDao.getFriendByAccountAndFiendId(account,
		// message.getAccount_id());
		// if (friend != null && friend.getStatus() == 1)
		// canaddfriend = false;
		// }
		sdf.setTimeZone(TimeZone.getTimeZone(tzone));
		Limit limit = message.getLimit();
		StringBuffer sb = new StringBuffer();
		sb.append(message.getNickname());
		if (message.getPmessage() != null && !message.getPmessage().equals(""))
			sb.append("--").append(message.getPmessage());
		sb.append("\r\n");
		sb
				.append(
						StringUtil.deleteImportantUnderlineSign(message
								.getContent())).append("\r\n");
		if (message.getUrl() != null)
			sb.append(message.getUrl()).append("\r\n");
		String mto = "";
		switch (limit.getPurview()) {
		case 3:
			Account toAccount = accountDao.getAccountById(limit.getMto());
			mto = toAccount.getNickname() + "(" + toAccount.getEmail() + ")";
			break;
		case 1:
			if (limit.getForumid() != null)
				mto = groupDao.getGroupById(limit.getForumid()).getName();
			else
				mto = "本群組";
			break;
		case -1:
			mto = "自己";
			break;
		case 12:
			mto = "社群";
			break;
		default:
			mto = "";
		}
		sb.append("發佈給：【").append(mto).append("】\r\n");

		List<Reply> replys = message.getReplys();
		for (Reply reply : replys) {
			String sign;
			if (reply.getType() == 3 && !userid.equals(reply.getRaccountid()))
				continue;// 如果是注释，而且你不是注释的的原作者，就不要显示
			else if (reply.getType() == 2)
				sign = "[憤]";
			else
				sign = "[注]";
			if (reply.getPurview() == 3) {
				if (userid.equals(reply.getRaccountid())
						|| userid.equals(reply.getMaccountid()))
					sign = "[私]";
				else
					continue;// 如果是别人的私密回复，就不要显示
			} else
				sign = "[推]";
			sb.append(reply.getNickname());
			if (reply.getPmessage() != null && !reply.getPmessage().equals(""))
				sb.append("--").append(reply.getPmessage());
			sb.append("\r\n").append(sign);
			sb.append(StringUtil.deleteImportantUnderlineSign(reply
					.getContent()));
			sb.append("[").append(sdf.format(reply.getInputdate())).append("]");
			sb.append("\r\n\r\n");
		}

		return sb.toString();
	}

	@Override
	public Hashtable<String, String> imGetUserPublicMessageLists(Date date,
			String accountid, String friendid, int start, int limit) {
		List<Friend> friends = friendDao.getFriendsByAccountId(friendid);
		List<Message> messages = messageDao.getPublicMessagesByDayAndAccounts(
				date, 5, friendid, friends, start, limit);
		return changeMessageFromListToHashtable(messages, 3, accountid);
	}

	@Override
	public Hashtable<String, String> imGetOwnMessageCategory(String accountid,
			int type) {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		List<Subscribe> subscribes = subscribeDao
				.getSubscribesByAccountIdAndType(accountid, type);
		for (Subscribe subscribe : subscribes) {
			if (type == 1) {
				Account account = accountDao.getAccountById(subscribe
						.getSubscribe());
				if (account != null)
					hash.put(subscribe.getId(), account.getNickname() + "("
							+ account.getEmail() + ")");
			} else
				hash.put(subscribe.getId(), subscribe.getComment());
		}
		return hash;
	}

	@Override
	public Hashtable<String, String> imGetOwnSubscribeMessageLists(Date date,
			String accountid, String subscribeid, int start, int limit) {
		Hashtable<String, String> table = new Hashtable<String, String>();
		List<Message> messages = messageDao.getOwnSubscribeMessagesByDate(date,
				5, accountid, subscribeid, start, limit);
		for (Message message : messages) {
			String title = StringUtil.splitString(message.getPmessage(), 40);
			if (!message.isReadr())
				title = title + "[新回復]";
			table.put(message.getId(), title);
		}
		return table;
	}

	@Override
	public Hashtable<String, String> imGetOwnTraceSubscribeMessageList(
			Date date, String accountid, int start, int limit) {
		List<Message> messages = new ArrayList<Message>();
		List<Message> cmessages = messageDao.getCommunityMessagesByDate(date,
				5, start, limit);
		List<Subscribe> subscribes = subscribeDao
				.getSubscribesByAccountId(accountid);
		for (Message m : cmessages) {
			if (isSubscribes(m, subscribes)) {
				Message m2 = new Message();
				m2.setAccount_id(m.getAccount_id());
				m2.setAvatar(m.getAvatar());
				m2.setContent(m.getContent());
				m2.setCdate(m.getCdate());
				m2.setId(m.getId());
				m2.setIm(m.getIm());
				m2.setNickname(m.getNickname());
				m2.setUdate(m.getUdate());
				m2.setPmessage(m.getPmessage());
				messages.add(m2);
			}
		}
		return changeMessageFromListToHashtable(messages, -1, accountid);
	}

	@Override
	public boolean imUpdateMessageContent(String accountid, String messageid,
			String content) {
		Message message = messageDao.getMessageById(messageid);
		if (message != null && accountid.equals(message.getAccount_id())) {
			message.setContent(content);
			return messageDao.saveMessage(message);
		}
		return false;
	}
}
