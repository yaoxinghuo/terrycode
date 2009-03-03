package com.microblog.data.dao.intf;

import java.util.List;

import com.microblog.data.model.Subscribe;

public interface ISubscribeDao {
	public List<Subscribe> getSubscribesByAccountId(String accountid);

	public List<Subscribe> getSubscribesByAccountIdAndType(String accountid,
			int type);

	public Subscribe getSubscribeByAccountIdAndUserId(String accountid,
			String userid);

	public Subscribe getSubscribeByAccountIdAndMessageId(String accountid,
			String messageid);

	public boolean saveSubscribe(Subscribe subscribe);

	public void deleteSubscribesByMessageId(String messageid);

	public boolean deleteSubscribe(Subscribe subscribe);
}
