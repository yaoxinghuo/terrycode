package com.terry.ipseeker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create：Sep 1, 2009 4:45:13 PM
 */
public class Loader {
	public static String DATA_PATH = null;

	static {
		String propName = "ipseeker.properties";
		ClassLoader classLoader = Loader.class.getClassLoader();
		File file = new File(classLoader.getResource(propName).getFile());

		if (file == null || !file.exists()) {
			System.err
					.println("can not load data path from ipseeker.properties. use default datapath:"
							+ new File("./data/").getAbsolutePath());
		} else {
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(file));
				DATA_PATH = properties.getProperty("ipseeker.data.path");
				// System.out.println("load data path:" + DATA_PATH);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					System.err
							.println("can not load data path from ipseeker.properties. use default datapath:"
									+ new File("./data/").getCanonicalPath());
				} catch (IOException e1) {
				}
			}
		}

		if (DATA_PATH == null) {
			DATA_PATH = "./data/";
		}

	}

	public static void main(String[] args) throws Exception {
		System.out.println(DATA_PATH);
	}
}
