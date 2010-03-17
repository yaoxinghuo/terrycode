package com.terry.weddingphoto.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Comment;
import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.util.EMF;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:36:04
 */
public class PhotoDaoImpl implements IPhotoDao {

	EntityManager em = EMF.get().createEntityManager();

	@Override
	public boolean savePhoto(Photo photo) {
		try {
			EntityManager em = EMF.get().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(photo);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getPhotos(int start, int limit) {
		Query query = em.createQuery("SELECT f FROM " + Photo.class.getName()
				+ " f");
		query.setFirstResult(start);
		if (limit > 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public Photo getPhotoById(String id) {
		Key key = KeyFactory.stringToKey(id);
		if (key == null || !key.isComplete())
			return null;
		return em.find(Photo.class, key);
	}

	@Override
	public boolean deleteCommentById(String cid) {
		EntityManager em = EMF.get().createEntityManager();
		Key key = KeyFactory.stringToKey(cid);
		if (key == null || !key.isComplete())
			return false;
		Comment c = em.find(Comment.class, key);
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(c);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByPhotoId(String pid, int start, int limit) {
		Query query = em.createQuery("SELECT c FROM " + Comment.class.getName()
				+ " c where c.pid=:pid");
		query.setParameter("pid", pid);
		query.setFirstResult(start);
		if (limit > 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public int saveComment(Comment comment) {
		try {
			EntityManager em = EMF.get().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			Key key = KeyFactory.stringToKey(comment.getPid());
			if (key == null || !key.isComplete())
				return -1;
			Photo p = em.find(Photo.class, key);
			p.setComment(p.getComment()+1);
			comment.setPhoto(p);
			tx.begin();
			em.persist(p);
			em.persist(comment);
			tx.commit();
			return p.getComment();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public boolean deletePhotoById(String pid) {
		EntityManager em = EMF.get().createEntityManager();
		Key key = KeyFactory.stringToKey(pid);
		if (key == null || !key.isComplete())
			return false;
		Photo p = em.find(Photo.class, key);
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(p);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
