package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.IAccountDao;
import cn.edu.jiangnan.lab.data.pojo.Account;

public class AccountDaoImpl extends HibernateDaoSupport implements IAccountDao {

	@Override
	public boolean deleteAccount(Account account) {
		if (account.getNo().equals("admin"))
			return false;
		try {
			super.getHibernateTemplate().delete(account);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Account getAccountById(String id) {
		return (Account) super.getHibernateTemplate().get(Account.class, id);
	}

	@Override
	public Account getAccountByNo(final String no) {
		return (Account) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return (Account) session.createQuery(
								"from Account a where a.no='" + no + "'")
								.uniqueResult();
					}
				});
	}

	@Override
	public Account getAccountByUsername(final String username) {
		return (Account) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return (Account) session.createQuery(
								"from Account a where a.username='" + username
										+ "'").uniqueResult();
					}
				});
	}

	@Override
	public boolean saveAccount(Account account) {
		try {
			super.getHibernateTemplate().saveOrUpdate(account);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Account> getUserAccounts(final int start, final int limit) {
		return (ArrayList<Account>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Account a where a.no!='admin' and a.admin in(1,4)");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Account> getAdminAccounts(final int start, final int limit) {
		return (ArrayList<Account>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Account a where a.no!='admin' and a.admin in(0,2,3)");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Account> getAccountsBySearch(final int start,
			final int limit, final String keyword, final String column) {
		return (ArrayList<Account>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Account a where a.no!='admin' and a."
										+ column + " like :keyword");
						query.setString("keyword", "%" + keyword + "%");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getAdminAccountsCount() {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session
								.createQuery(
										"select count(a) from Account a where a.no!='admin' and a.admin in(0,2,3)")
								.uniqueResult();
					}
				});
	}

	@Override
	public long getUserAccountsCount() {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session
								.createQuery(
										"select count(a) from Account a where a.no!='admin' and a.admin in(1,4)")
								.uniqueResult();
					}
				});
	}

	@Override
	public long getAccountsCountBySearch(final String keyword,
			final String column) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("select count(a) from Account a where a.no!='admin' and a."
										+ column + " like :keyword");
						query.setString("keyword", "%" + keyword + "%");
						return query.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Account> getAllAdmins() {
		return (ArrayList<Account>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session
								.createQuery(
										"from Account a where a.no!='admin' and a.admin>1")
								.list();
					}
				});
	}
}
