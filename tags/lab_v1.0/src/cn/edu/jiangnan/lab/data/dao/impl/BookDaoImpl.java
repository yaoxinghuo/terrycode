package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.IBookDao;
import cn.edu.jiangnan.lab.data.pojo.Book;

public class BookDaoImpl extends HibernateDaoSupport implements IBookDao {

	@Override
	public boolean deleteBook(Book book) {
		try {
			super.getHibernateTemplate().delete(book);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getBooksByEquipId(final int start, final int limit,
			final String id) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Book b where b.equip_id='"
										+ id
										+ "' and b.action!=2 order by b.input desc");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountBooksByEquipId(final String id) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"select count(b) from Book b where b.equip_id='"
										+ id + "' and b.action!=2")
								.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getMyUnfonfirmedBooksByEquipId(final String user_id,
			final String equip_id, final Date start) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						String sql = "from Book b where b.equip_id=:equipid and b.user_id=:userid and b.action!=1 and b.start>=:start order by b.input desc";
						Query query = session.createQuery(sql);
						query.setString("equipid", equip_id);
						query.setString("userid", user_id);
						query.setTimestamp("start", start);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getConfirmedBooksByEquipId(final String id,
			final Date start) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						String sql = "from Book b where b.equip_id=:id and b.action=1 and b.start>=:start order by b.input desc";
						Query query = session.createQuery(sql);
						query.setString("id", id);
						query.setTimestamp("start", start);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getBooksByUserId(final int start, final int limit,
			final String id) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session
								.createQuery("from Book b where b.user_id='"
										+ id + "' order by b.input desc");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountBooksByUserId(final String id) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"select count(b) from Book b where b.user_id='"
										+ id + "'").uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getBooksLog(final int start, final int limit,
			final int action, final Date startDate, final Date endDate,
			final String keyword, final String column) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Calendar c = Calendar.getInstance();
						c.setTime(endDate);
						c.add(Calendar.DAY_OF_MONTH, 1);
						StringBuffer sb = new StringBuffer(
								"from Book b where b.input<:endDate ");
						if (startDate != null)
							sb.append("and b.input>:startDate ");
						if (action != -1)
							sb.append("and b.action=:action ");
						if (!keyword.equals("")) {
							sb.append(" and b.").append(column);
							sb.append(" like :keyword");
						}
						sb.append(" order by b.input desc");
						Query query = session.createQuery(sb.toString());
						if (startDate != null)
							query.setDate("startDate", startDate);
						query.setDate("endDate", c.getTime());
						if (action != -1)
							query.setInteger("action", action);
						if (!keyword.equals(""))
							query.setString("keyword", "%" + keyword + "%");
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountBooksLog(final int action, final Date startDate,
			final Date endDate, final String keyword, final String column) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Calendar c = Calendar.getInstance();
						c.setTime(endDate);
						c.add(Calendar.DAY_OF_MONTH, 1);
						StringBuffer sb = new StringBuffer(
								"select count(b) from Book b where b.input<:endDate");
						if (startDate != null)
							sb.append(" and b.input>:startDate");
						if (action != -1)
							sb.append(" and b.action=:action");
						if (!keyword.equals("")) {
							sb.append(" and b.").append(column);
							sb.append(" like :keyword");
						}
						Query query = session.createQuery(sb.toString());
						if (startDate != null)
							query.setDate("startDate", startDate);
						query.setDate("endDate", c.getTime());
						if (action != -1)
							query.setInteger("action", action);
						if (!keyword.equals(""))
							query.setString("keyword", "%" + keyword + "%");
						return query.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getPrBooksByAdminType(final int start,
			final int limit, final int type, final String adminName) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Book b where b.action=0");
						if (type != 0) {
							sb.append(" and b.type=").append(type);
							sb
									.append(" and (b.equip.admin like :adminName or b.equip.admin='')");
						}
						sb.append(" order by b.input desc");
						Query query = session.createQuery(sb.toString());
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						if (type != 0)
							query.setString("adminName", "%" + adminName + "%");
						return query.list();
					}
				});
	}

	@Override
	public long getCountPrBooksByAdminType(final int type,
			final String adminName) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb
								.append("select count(b) from Book b where b.action=0");
						if (type != 0) {
							sb.append(" and b.type=").append(type);
							sb
									.append(" and (b.equip.admin like :adminName or b.equip.admin='')");
						}
						Query query = session.createQuery(sb.toString());
						if (type != 0)
							query.setString("adminName", "%" + adminName + "%");
						return query.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Book> getAfBooksByAdminType(final int start,
			final int limit, final int type, final String adminName) {
		return (ArrayList<Book>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Book b where b.action=1");
						if (type != 0) {
							sb.append(" and b.type=").append(type);
							sb
									.append(" and (b.equip.admin like :adminName or b.equip.admin='')");
						}
						sb.append(" order by b.input desc");
						Query query = session.createQuery(sb.toString());
						query.setFirstResult(start);
						if (limit != 0)
							query.setMaxResults(limit);
						if (type != 0)
							query.setString("adminName", "%" + adminName + "%");
						return query.list();
					}
				});
	}

	@Override
	public long getCountAfBooksByAdminType(final int type,
			final String adminName) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb
								.append("select count(b) from Book b where b.action=1");
						if (type != 0) {
							sb.append(" and b.type=").append(type);
							sb
									.append(" and (b.equip.admin like :adminName or b.equip.admin='')");
						}
						Query query = session.createQuery(sb.toString());
						if (type != 0)
							query.setString("adminName", "%" + adminName + "%");
						return query.uniqueResult();
					}
				});
	}

	@Override
	public boolean saveBook(Book book) {
		try {
			super.getHibernateTemplate().saveOrUpdate(book);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Book getBookById(String id) {
		return (Book) super.getHibernateTemplate().get(Book.class, id);
	}

	@Override
	public int bookClean() {
		return (Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						String sql = "delete from book where book.end<(now() - INTERVAL 1 MONTH)";
						// String sql = "delete from book where book.end<(now()
						// - INTERVAL 1 WEEK)";
						return session.createSQLQuery(sql).executeUpdate();
					}
				});
	}

}
