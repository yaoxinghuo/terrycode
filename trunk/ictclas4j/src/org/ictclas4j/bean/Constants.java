package org.ictclas4j.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create£ºAug 13, 2009 4:17:08 PM
 */
public class Constants {
	static Logger logger = Logger.getLogger(Constants.class);

	public static String DATA_PATH = null;

	static {
		String propName = "ictclas4j.properties";
		ClassLoader classLoader = Constants.class.getClassLoader();
		File file = new File(classLoader.getResource(propName).getFile());

		if (file == null || !file.exists()) {
			logger.error("can not load data path from ictclas4j.properties. use default datapath:"
					+ new File("./data/").getAbsolutePath());
		} else {
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(file));
				DATA_PATH = properties.getProperty("ictclas4j.data.path");
				logger.info("load data path:" + DATA_PATH);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					logger.error("can not load data path from ictclas4j.properties. use default datapath:"
							+ new File("./data/").getCanonicalPath());
				} catch (IOException e1) {
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// File file = new File("ictclas4j.properties");
		// System.out.println(file.getCanonicalPath());
		ClassLoader classLoader = Constants.class.getClassLoader();
		URL url = classLoader.getResource("ictclas4j.properties");
		System.out.println(url.getFile());
	}
}
