package com.terry.weddingphoto.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;
import com.terry.weddingphoto.model.Thumb;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 上午10:23:18
 */
public class PhotoUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3274040408352222112L;

	private ImagesService imagesService = ImagesServiceFactory
			.getImagesService();
	private UserService userService = UserServiceFactory.getUserService();
	private IPhotoDao photoDao = new PhotoDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		JSONObject jo = new JSONObject();
		try {
			jo.put("result", false);
			jo.put("message", "对不起，未能保存上传的文件，请稍候再试！");
		} catch (JSONException e1) {
		}
		if (!userService.isUserLoggedIn() || !userService.isUserAdmin()) {
			try {
				jo.put("message", "你无权上传文件！");
			} catch (JSONException e) {
			}
		} else {
			try {
				ServletFileUpload upload = new ServletFileUpload();
				upload.setSizeMax(1000000);

				try {
					FileItemIterator iterator = upload.getItemIterator(req);
					while (iterator.hasNext()) {
						FileItemStream item = iterator.next();
						InputStream in = item.openStream();

						if (item.isFormField()) {

						} else {
							String fileName = item.getName();
							try {
								byte[] b = IOUtils.toByteArray(in);
								Image oldImage = ImagesServiceFactory
										.makeImage(b);
								Transform resize1 = ImagesServiceFactory
										.makeResize(600, 800);
								Transform resize2 = ImagesServiceFactory
										.makeResize(100, 80);
								Image newImage1 = imagesService.applyTransform(
										resize1, oldImage);
								Image newImage2 = imagesService.applyTransform(
										resize2, oldImage);
								byte[] thumbImageData1 = newImage1
										.getImageData();
								byte[] thumbImageData2 = newImage2
										.getImageData();

								Photo p = new Photo();
								p.setCdate(new Date());
								p.setData(new Blob(thumbImageData1));
								p.setFilename(fileName);
								p.setHeight(oldImage.getHeight());
								p.setWidth(oldImage.getWidth());
								p.setSize(b.length);
								p.setComment(true);

								Thumb t = new Thumb();
								t.setCdate(p.getCdate());
								t.setData(new Blob(thumbImageData2));
								t.setPid(p.getId());
								p.setThumb(t);

								if (photoDao.savePhoto(p)) {
									jo.put("result", true);
									jo.put("message", "文件：" + fileName
											+ " 已成功存入数据库");
								}
							} finally {
								IOUtils.closeQuietly(in);
							}

						}
					}
				} catch (SizeLimitExceededException e) {
					jo.put("message", "您最多允许上传 (" + e.getPermittedSize()
							+ ") 实际已上传 (" + e.getActualSize() + ")");
				}
			} catch (Exception ex) {
				try {
					jo.put("message", "对不起，程序错误，请稍候再试！" + ex.getMessage());
				} catch (JSONException e) {
				}
			}
		}
		PrintWriter out = resp.getWriter();
		out.print(jo.toString());
	}

}
