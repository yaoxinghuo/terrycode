package com.terry.weddingphoto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:55:56
 */
public class PhotoViewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4072411700253346694L;

	private ImagesService imagesService = ImagesServiceFactory
			.getImagesService();
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
		if (!StringUtil.isEmptyOrWhitespace(id)) {
			Photo p = photoDao.getPhotoById(id);
			if (p != null) {
				String width = req.getParameter("w");
				String height = req.getParameter("h");
				if (!StringUtil.isEmptyOrWhitespace(width)
						&& !StringUtil.isEmptyOrWhitespace(height)) {
					int w = Integer.parseInt(width);
					int h = Integer.parseInt(height);
					Image oldImage = ImagesServiceFactory.makeImage(p.getData()
							.getBytes());
					Transform resize = ImagesServiceFactory.makeResize(w, h);
					Image newImage = imagesService.applyTransform(resize,
							oldImage);
					return newImage.getImageData();
				} else
					return p.getData().getBytes();
			}
		}
		return null;
	}

}
