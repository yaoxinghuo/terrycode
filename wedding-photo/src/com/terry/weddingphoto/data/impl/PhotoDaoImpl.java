package com.terry.weddingphoto.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.weddingphoto.data.intf.IPhotoDao;
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
			e.printStackTrace();
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
}
