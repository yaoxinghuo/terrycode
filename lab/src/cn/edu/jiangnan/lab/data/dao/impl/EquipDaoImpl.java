package cn.edu.jiangnan.lab.data.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.jiangnan.lab.data.dao.intf.IEquipDao;
import cn.edu.jiangnan.lab.data.pojo.Equip;

public class EquipDaoImpl extends HibernateDaoSupport implements IEquipDao {

	@Override
	public boolean deleteEquip(Equip equip) {
		try {
			super.getHibernateTemplate().delete(equip);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public Equip getEquipById(String id) {
		return (Equip) super.getHibernateTemplate().get(Equip.class, id);
	}

	@Override
	public boolean saveEquip(Equip equip) {
		try {
			super.getHibernateTemplate().saveOrUpdate(equip);
		} catch (org.springframework.dao.DataAccessException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Equip> getEquipsByType(final int start, final int limit,
			final int type, final boolean checkpub) {
		return (ArrayList<Equip>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer("from Equip as e ");
						if (checkpub)
							sb.append("where e.pub is true ");
						if (type != 0) {
							if (checkpub)
								sb.append("and e.type= ").append(type);
							else
								sb.append("where e.type= ").append(type);
						}
						sb.append(" order by e.counter desc");
						Query q = session.createQuery(sb.toString());
						q.setFirstResult(start);
						if (limit != 0)
							q.setMaxResults(limit);
						return q.list();
					}
				});
	}

	@Override
	public long getCountEquipsByType(final int type, final boolean checkpub) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer(
								"select count(e) from Equip as e ");
						if (checkpub)
							sb.append("where e.pub is true ");
						if (type != 0) {
							if (checkpub)
								sb.append("and e.type= ").append(type);
							else
								sb.append("where e.type= ").append(type);
						}
						return session.createQuery(sb.toString())
								.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Equip> getEquipsBySearch(final int start, final int limit,
			final String keyword, final String column, final boolean checkpub) {
		return (ArrayList<Equip>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Equip as e where e.");
						sb.append(column).append(" like :keyword");
						if (checkpub)
							sb.append(" and e.pub is true");
						sb.append(" order by e.counter desc");
						Query q = session.createQuery(sb.toString());
						q.setString("keyword", "%" + keyword + "%");
						q.setFirstResult(start);
						if (limit != 0)
							q.setMaxResults(limit);
						return q.list();
					}
				});
	}

	@Override
	public long getCountEquipsBySearch(final String keyword,
			final String column, final boolean checkpub) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("select count(e) from Equip as e where e.");
						sb.append(column).append(" like :keyword");
						if (checkpub)
							sb.append(" and e.pub is true");
						Query q = session.createQuery(sb.toString());
						q.setString("keyword", "%" + keyword + "%");
						return q.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Equip> getEquips(final int start, final int limit,
			final boolean checkpub) {
		return (ArrayList<Equip>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("from Equip e");
						if (checkpub)
							sb.append(" where e.pub is true");
						Query q = session.createQuery(sb.toString());
						q.setFirstResult(start);
						if (limit != 0)
							q.setMaxResults(limit);
						return q.list();
					}
				});
	}

	@Override
	public long getCountEquips(final boolean checkpub) {
		return (Long) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						StringBuffer sb = new StringBuffer();
						sb.append("select count(e) from Equip e");
						if (checkpub)
							sb.append(" where e.pub is true");
						return session.createQuery(sb.toString())
								.uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Equip> getImageEquips() {
		return (ArrayList<Equip>) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createQuery(
								"from Equip e where e.image is not null")
								.list();

					}
				});
	}

}
