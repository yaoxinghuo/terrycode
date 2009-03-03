package com.microblog.data.dao.intf;

import java.util.ArrayList;

import com.microblog.data.model.Message;
import com.microblog.data.model.Reply;

public interface IReplyDao {
	public boolean saveReply(Reply reply);

	public boolean deleteReply(Reply reply);

	public Reply getReplyById(String id);

	public ArrayList<Reply> getReplysByMessage(Message message);
}
