package com.terry.straincatalog;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Apr 30, 2010 9:22:20 PM
 */
public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 656562992155229200L;

	private Image img;
	private int width;
	private int height;

	public DrawPanel(Image img, int width, int height) {
		this.img = img;
		this.width = width;
		this.height = height;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, width, height, null);
	}
}
