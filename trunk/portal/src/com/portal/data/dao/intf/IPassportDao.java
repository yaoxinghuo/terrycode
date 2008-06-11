package com.portal.data.dao.intf;

import com.portal.data.pojo.Passport;

public interface IPassportDao {
	public Passport getPassportById(String id);

	public Passport getPassportByUsername(String username);

	public boolean savePassport(Passport passport);

	public boolean deletePassport(Passport passport);
}
