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
import com.terry.weddingphoto.constants.Constants;
import com.terry.weddingphoto.data.impl.PhotoDaoImpl;
import com.terry.weddingphoto.data.intf.IPhotoDao;
import com.terry.weddingphoto.model.Photo;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 上午10:23:18
 */
public class PhotoUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3274040408352222112L;

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
		if (!Constants.CAN_UPLOAD) {
			try {
				jo.put("message", "你无权上传文件，请开启web.xml中的canUploadPhotos开关");
			} catch (JSONException e) {
			}
		} else {
			try {
				ServletFileUpload upload = new ServletFileUpload();
				upload.setSizeMax(1048576);

				try {
					FileItemIterator iterator = upload.getItemIterator(req);
					while (iterator.hasNext()) {
						FileItemStream item = iterator.next();
						InputStream in = item.openStream();

						if (item.isFormField()) {

						} else {
							String fileName = item.getName();
							try {
								byte[] data = IOUtils.toByteArray(in);
								Photo p = new Photo();
								p.setCdate(new Date());
								p.setData(new Blob(data));
								p.setFilename(fileName);
								p.setComment(0);

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
