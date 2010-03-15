package com.terry.weddingphoto.data.intf;

import java.util.List;

import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.model.Thumb;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:35:01
 */
public interface IPhotoDao {
	public boolean savePhoto(Photo photo);

	public Photo getPhotoByThumbId(String tid);

	public Photo getPhotoById(String id);
	
	public Thumb getThumbById(String tid);

	public List<Thumb> getThumbs(int start, int limit);
}
