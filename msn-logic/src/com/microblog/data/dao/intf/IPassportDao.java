package com.microblog.data.dao.intf;

import com.microblog.data.model.Passport;

public interface IPassportDao {
	public Passport getPassportByEmail(String email);

	public Passport getPassportByAccountId(String account_id);

	public boolean updatePassport(Passport passport);
}
