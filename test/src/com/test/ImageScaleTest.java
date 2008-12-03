package com.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageScaleTest {
	public static void main(String[] args) throws Exception {
		scale("e:\\a.jpg", "e:\\b.jpg");
	}

	public static void scale(String srcImageFile, String result)
			throws Exception {
		BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长

		if (width < 60 || height < 45)
			throw new Exception();

		double scale = 1;
		if (width > 600 || height > 450) {
			scale = (double) width / (double) 600 > (double) height
					/ (double) 450 ? (double) width / (double) 600
					: (double) height / (double) 450;
			width = (int) (width / scale);
			height = (int) (height / scale);
		}

		Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
	}

}
