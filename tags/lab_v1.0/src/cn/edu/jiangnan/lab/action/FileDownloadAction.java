package cn.edu.jiangnan.lab.action;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import cn.edu.jiangnan.lab.data.pojo.FileInfo;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String name;
	private String allowTypes;
	private ArrayList<FileInfo> files = new ArrayList<FileInfo>();
	DecimalFormat df = new DecimalFormat("0.0");
	private static final String FILE_PATH = "/userfiles/file/download/";
	private String[] allowExtentions;

	public ArrayList<FileInfo> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<FileInfo> files) {
		this.files = files;
	}

	@Override
	public String execute() throws Exception {
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(FILE_PATH)
				+ "/" + fileName);
		this.name = URLEncoder.encode(fileName, "UTF-8");

		if (file.exists())
			return SUCCESS;
		else
			return "error404";
	}

	public String list() throws Exception {
		File folder = new File(ServletActionContext.getServletContext()
				.getRealPath(FILE_PATH));
		if (!folder.isDirectory())
			return "error404";
		File[] f = folder.listFiles(new FileFilter() {
			public boolean accept(File f) {
				String name = f.getName().toLowerCase();
				for (int i = 0; i < allowExtentions.length; i++) {
					if (name.endsWith(allowExtentions[i]))
						return true;
				}
				return false;
			}
		});

		for (int i = 0; i < f.length; i++) {
			FileInfo fileInfo = new FileInfo();
			File file = f[i];
			long length = file.length();
			if (length > 1024 * 1024 * 20)
				continue;
			else if (length > 1024 * 1024)
				fileInfo.setSize(df.format((float) length / 1024 / 1024)
						+ " MB");
			else
				fileInfo.setSize(df.format((float) length / 1024) + " KB");
			fileInfo.setName(file.getName());
			files.add(fileInfo);
		}
		return "index_download";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InputStream getInputStream() {
		try {
			return ServletActionContext.getServletContext()
					.getResourceAsStream(FILE_PATH + fileName);
		} catch (RuntimeException e) {
			return null;
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		/* 要不要加下面的调节一下 */
		/*
		 * 这里传过来的参数是中文，需要在server.xml中加上 <Connector port="8080"
		 * protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443"
		 * URIEncoding="utf-8"/> <Connector port="8009" protocol="AJP/1.3"
		 * redirectPort="8443" URIEncoding="utf-8"/> 如果不加把下面相关的代码取消注释
		 */
		// try {
		// this.fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// this.fileName = fileName;
		// }
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
		allowExtentions = allowTypes.split(",");
	}
}
