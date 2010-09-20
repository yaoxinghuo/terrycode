package com.terry.compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-9-20 下午02:19:18
 */
public class CSSCompressor {
	private static final String charset = "UTF-8";
	private static final int linebreakpos = -1;

	public static void main(String[] args) throws Exception {
		// compressFile("E:\\Java\\lab\\src\\js-original\\app\\login.css",
		// "E:\\Java\\lab\\WebContent\\js\\app\\login.css");
		compressFolder("E:\\workspace\\lab\\WebContent\\resources\\css\\", "E:\\css\\");
	}

	/*
	 * 压缩单个css文件
	 */
	public static void compressFile(String inputFileName, String outputFileName) {
		if (compress(new File(inputFileName), new File(outputFileName)))
			System.out.println("Successed.\tSource:" + inputFileName
					+ "\tDestination:" + outputFileName);
		else
			System.err.println("Failed.\tSource" + inputFileName);
	}

	/*
	 * 压缩文件夹下的所有css和子文件下的css，目标路径下如果没有目录会自己建，文件会覆盖掉哦
	 */
	public static void compressFolder(String inputFolderName,
			String outputFolderName) throws Exception {
		File inputFolder = new File(inputFolderName);
		File outputFolder = new File(outputFolderName);
		System.out.println("------Compress folder:\t"
				+ inputFolder.getCanonicalPath() + "\tDestination:"
				+ outputFolder.getCanonicalPath());
		for (File f : inputFolder.listFiles()) {// 两个for不写在一起，让他先处理文件,然后文件夹
			if (f.getName().toLowerCase().endsWith("css"))
				compressFile(f.getCanonicalPath(), outputFolder
						.getCanonicalPath()
						+ File.separator + f.getName());
		}
		for (File f : inputFolder.listFiles()) {
			if (f.isDirectory())
				compressFolder(f.getCanonicalPath(), outputFolder
						.getCanonicalPath()
						+ File.separator + f.getName());
		}
	}

	private static boolean compress(File inputFilename, File outputFilename) {
		Reader in = null;
		Writer out = null;
		try {
			in = new InputStreamReader(new FileInputStream(inputFilename),
					charset);

			CssCompressor compressor = new CssCompressor(in);
			in.close();
			in = null;
			if (outputFilename == null)
				out = new OutputStreamWriter(System.out, charset);
			else {
				if (!outputFilename.getParentFile().exists()) {
					outputFilename.getParentFile().mkdir();
					System.out.println("******Make directory:\t"
							+ outputFilename.getParent());
				}
				out = new OutputStreamWriter(new FileOutputStream(
						outputFilename), charset);
			}
			compressor.compress(out, linebreakpos);
			out.flush();
			out.close();
			out = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
