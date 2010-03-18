package com.terry.weddingphoto.data.intf;

import java.util.List;

import com.terry.weddingphoto.model.Comment;
import com.terry.weddingphoto.model.Photo;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:35:01
 */
public interface IPhotoDao {
	public boolean savePhoto(Photo photo);

	public boolean saveOrUpdatePhoto(String filename, byte[] data);

	public Photo getPhotoById(String id);

	public boolean deletePhotoById(String pid);

	public List<Photo> getPhotos(int start, int limit);

	public List<Photo> getPhotosByFilename(String filename);

	public boolean checkPhotoExists(String filename);

	public List<Comment> getCommentsByPhotoId(String pid, int start, int limit);

	public int saveComment(Comment comment);

	public int deleteCommentById(String cid);
}
