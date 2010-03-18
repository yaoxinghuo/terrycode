package com.terry.weddingphoto.util;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.terry.weddingphoto.constants.Constants;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Mar 17, 2010 9:28:41 PM
 */
public class PhotoCache {
	private static Cache cache;

	private static final String PHOTO_CACHE = "photo-cache";

	private static ImagesService imagesService = ImagesServiceFactory
			.getImagesService();
	private static IPhotoDao photoDao = new PhotoDaoImpl();

	static {
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
		} catch (CacheException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public static byte[] getPhotoData(String pid, String width, String height) {
		if (width == null)
			width = "";
		if (height == null)
			height = "";
		String key = PHOTO_CACHE + "-" + pid + "-w" + width + "-h" + height;
		Object o = cache.get(key);
		if (o != null)
			return (byte[]) o;
		byte[] data = getPhotoDataFromDB(pid, width, height);
		if (data != null && data.length < Constants.PHOTO_BYTES_LIMIT) {
			cache.put(key, data);
		}
		return data;
	}

	private static byte[] getPhotoDataFromDB(String pid, String width,
			String height) {
		Photo p = photoDao.getPhotoById(pid);
		if (p != null) {
			byte[] data = p.getData().getBytes();
			if (!StringUtil.isEmptyOrWhitespace(width)
					&& !StringUtil.isEmptyOrWhitespace(height)) {
				int w = Integer.parseInt(width);
				int h = Integer.parseInt(height);
				Image oldImage = ImagesServiceFactory.makeImage(data);
				if (w <= 0)
					w = oldImage.getWidth();
				if (h <= 0)
					h = oldImage.getHeight();
				if (oldImage.getWidth() > w || oldImage.getHeight() > h) {
					Transform resize = ImagesServiceFactory.makeResize(w, h);
					Image newImage = imagesService.applyTransform(resize,
							oldImage, ImagesService.OutputEncoding.JPEG);
					return newImage.getImageData();
				} else
					return data;
			} else
				return data;
		}
		return null;
	}

}
