package com.microblog.util;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CompareImage {
	private static boolean comparePixels(Image img1, Image img2, int x, int y,
			int w, int h) {
		int[] pixels1 = new int[w * h];
		int[] pixels2 = new int[w * h];
		PixelGrabber pg1 = new PixelGrabber(img1, x, y, w, h, pixels1, 0, w);
		PixelGrabber pg2 = new PixelGrabber(img2, x, y, w, h, pixels2, 0, w);
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {
			// System.err.println("interrupted waiting for pixels!");
			return false;
		}
		if ((pg1.getStatus() & pg2.getStatus() & ImageObserver.ABORT) != 0) {
			// System.err.println("image fetch aborted or errored");
			return false;
		}
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				if (pixels1[j * w + i] != pixels2[j * w + i])
					return false;
			}
		}
		return true;
	}

	public static boolean compareImage(File f1, File f2, int x, int y, int w,
			int h) {
		try {
			Image img1 = ImageIO.read(f1);
			Image img2 = ImageIO.read(f2);

			return comparePixels(img1, img2, x, y, w, h);
		} catch (IOException e) {
			return false;
		}
	}

}
