package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.INoticeDao;
import cn.edu.jiangnan.lab.data.pojo.Notice;

public class NoticeDaoImpl extends HibernateDaoSupport implements INoticeDao {

	@Override
	public boolean deleteNotice(Notice notice) {
		try {
			super.getHibernateTemplate().delete(notice);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Notice> getNotices(final int type, final int limit,
			final boolean pub) {
		return (ArrayList<Notice>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Notice n where n.pub is ");
						if (pub)
							sb.append("true");
						else
							sb.append("false");
						if (type != 0)
							sb.append(" and n.type=").append(type);
						sb.append(" order by n.date desc");
						Query query = session.createQuery(sb.toString());
						if (limit != 0)
							query.setMaxResults(limit);
						return query.list();
					}
				});
	}

	@Override
	public long getCountNotices(final boolean pub) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("select count(n) from Notice n");
						if (pub)
							sb.append(" where n.pub is true");
						else
							sb.append(" where n.pub is false");
						return session.createQuery(sb.toString())
								.uniqueResult();
					}
				});
	}

	@Override
	public boolean saveNotice(Notice notice) {
		try {
			super.getHibernateTemplate().saveOrUpdate(notice);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Notice getNoticeById(String id) {
		return (Notice) super.getHibernateTemplate().get(Notice.class, id);
	}

}
