package com.terry.weddingphoto.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.model.Thumb;
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

			EntityManager em2 = EMF.get().createEntityManager();
			Key key = KeyFactory.stringToKey(photo.getThumb().getId());
			if (key == null || !key.isComplete())
				return false;
			Thumb t2 = em2.find(Thumb.class, key);
			EntityTransaction tx2 = em2.getTransaction();
			tx2.begin();
			t2.setPid(photo.getId());
			em2.persist(t2);
			tx2.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Photo getPhotoByThumbId(String tid) {
		Key key = KeyFactory.stringToKey(tid);
		if (key == null || !key.isComplete())
			return null;
		Thumb t = em.find(Thumb.class, key);
		if (t == null)
			return null;
		return getPhotoById(t.getPid());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Thumb> getThumbs(int start, int limit) {
		Query query = em.createQuery("SELECT t FROM " + Thumb.class.getName()
				+ " t");
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
	public Thumb getThumbById(String tid) {
		Key key = KeyFactory.stringToKey(tid);
		if (key == null || !key.isComplete())
			return null;
		return em.find(Thumb.class, key);
	}

}
