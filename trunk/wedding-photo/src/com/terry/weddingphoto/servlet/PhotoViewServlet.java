package com.terry.weddingphoto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.model.Thumb;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:55:56
 */
public class PhotoViewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4072411700253346694L;

	private IPhotoDao photoDao = new PhotoDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] data = getImageData(req);
		if (data == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					"can not find photo ");
			return;
		}
		Image image = ImagesServiceFactory.makeImage(data);
		resp.setContentType("image/"
				+ image.getFormat().toString().toLowerCase());
		ServletOutputStream responseOutputStream = resp.getOutputStream();
		IOUtils.write(data, responseOutputStream);
	}

	private byte[] getImageData(HttpServletRequest req) {
		String id = req.getParameter("id");
		if (id != null) {
			Photo p = photoDao.getPhotoByThumbId(id);
			if (p != null)
				return p.getData().getBytes();
		}
		id = req.getParameter("pid");
		if (id != null) {
			Photo p = photoDao.getPhotoById(id);
			if (p != null)
				return p.getData().getBytes();
		}
		id = req.getParameter("tid");
		if (id != null) {
			Thumb t = photoDao.getThumbById(id);
			if (t != null)
				return t.getData().getBytes();
		}
		return null;
	}

}
