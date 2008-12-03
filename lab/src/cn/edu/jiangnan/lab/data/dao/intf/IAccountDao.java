package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;

import cn.edu.jiangnan.lab.data.pojo.Account;

public interface IAccountDao {
	public Account getAccountById(String id);

	public Account getAccountByNo(String no);

	public Account getAccountByUsername(String username);

	public boolean saveAccount(Account account);

	public boolean deleteAccount(Account account);

	public ArrayList<Account> getAdminAccounts(int start, int limit);
	
	public ArrayList<Account> getUserAccounts(int start, int limit);
	
	public ArrayList<Account> getAccountsBySearch(int start, int limit, String keyword, String column);
	
	public long getAdminAccountsCount();
	
	public long getUserAccountsCount();
	
	public long getAccountsCountBySearch(String keyword, String column);

	public ArrayList<Account> getAllAdmins();

}
