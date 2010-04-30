package com.terry.straincatalog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import com.terry.straincatalog.db.DBUtil;
import com.terry.straincatalog.model.Attachment;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Apr 30, 2010 5:10:51 PM
 */
public class AttachmentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2373884687011962629L;

	public AttachmentPanel(final Attachment a) {
		final String fileName = a.getFileName();
		final JTextField descField = new JTextField(15);
		descField.setText(a.getDescription());
		this.add(new JLabel("附件说明:"));
		this.add(descField);
		this.add(new JLabel("文件名:"));
		JTextField fileNameField = new JTextField(fileName, 15);
		fileNameField.setEditable(false);
		this.add(fileNameField);
		String downloadName = null;
		final boolean isImage = isImage(fileName);
		if (isImage) {
			downloadName = "查看图片";
		} else
			downloadName = "下载附件";
		JButton downloadButton = new JButton(downloadName);
		downloadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (isImage) {
						JFrame frame = new JFrame(a.getDescription());
						Image img = ImageIO.read(new ByteArrayInputStream(a
								.getData()));
						int width = img.getWidth(null);
						int height = img.getHeight(null);
						if (width > 800 || height > 600) {
							float rw = (float) width / (float) 800;
							float rh = (float) height / (float) 600;
							if (rw > rh) {
								height = (int) (height / rw);
								width = 800;
							} else {
								width = (int) (width / rh);
								height = 600;
							}
						}
						DrawPanel drawPanel = new DrawPanel(img, width, height);
						frame.getContentPane().add(drawPanel);
						frame.setSize(600, 400);
						frame.setVisible(true);
					} else {
						JFileChooser chooser = new JFileChooser();
						chooser
								.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int i = chooser.showSaveDialog(null); // 打开保存文件对话框

						if (i == javax.swing.JFileChooser.APPROVE_OPTION) {
							File dir = chooser.getSelectedFile();

							File f = new File(dir.getPath().concat(
									File.separator).concat(fileName));
							if (f.exists()) {
								i = javax.swing.JOptionPane.showConfirmDialog(
										null, "该文件已经存在，确定要覆盖吗？", "确认覆盖",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE);
								if (i != javax.swing.JOptionPane.YES_OPTION)
									return;
							}
							FileOutputStream fos = new FileOutputStream(f);
							fos.write(a.getData());
							fos.flush();
							fos.close();
							showMessage2(Color.BLUE, "您已成功下载附件！");
						}
					}
				} catch (IOException e) {
					showMessage2(Color.RED, "出现错误，请稍候再试！");
				}
			}
		});
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null,
						"您确定要删除选中的附件？", "确认删除", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					DBUtil db = DBUtil.getInstance();
					if (db.deleteAttachmentById(a.getId())) {
						showMessage2(Color.BLUE, "您已成功删除附件！");
						refresh2();
					} else {
						showMessage2(Color.RED, "未能删除附件，请稍候再试！");
					}
				}
			}
		});
		JButton reuploadButton = new JButton("更新附件");
		reuploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (descField.getText().trim().equals("")) {
					showMessage2(Color.ORANGE, "附件说明不能为空！");
					return;
				}
				JFileChooser chooser = new JFileChooser();
				int state = chooser.showOpenDialog(null);
				if (state != 0)
					return;
				File file = chooser.getSelectedFile();
				if (file == null || !file.exists() || file.isDirectory())
					return;
				try {
					Attachment attachment = new Attachment();
					attachment.setDescription(descField.getText());
					attachment.setStrainId(a.getStrainId());
					byte[] b = IOUtils.toByteArray(new FileInputStream(file));
					attachment.setData(b);
					attachment.setFileName(file.getName());
					DBUtil db = DBUtil.getInstance();
					if (db.updateAttachment(a.getId(), attachment)) {
						descField.setText("");
						showMessage2(Color.BLUE, "您已成功保存附件！");
						refresh2();
					} else {
						showMessage2(Color.RED, "未能保存附件，请稍候再试！");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		this.add(downloadButton);
		this.add(deleteButton);
		this.add(reuploadButton);
	}

	public AttachmentPanel(final String id) {
		Font font = new Font(null, Font.BOLD, 12);
		JLabel newLabel = new JLabel("新增附件");
		newLabel.setFont(font);
		newLabel.setForeground(Color.BLUE);
		this.add(newLabel);
		this.add(new JLabel("附件说明:"));
		final JTextField descField = new JTextField(15);
		this.add(descField);
		JButton uploadButton = new JButton("选择文件并保存");
		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (descField.getText().trim().equals("")) {
					showMessage2(Color.ORANGE, "附件说明不能为空！");
					return;
				}
				JFileChooser chooser = new JFileChooser();
				int state = chooser.showOpenDialog(null);
				if (state != 0)
					return;
				File file = chooser.getSelectedFile();
				if (file == null || !file.exists() || file.isDirectory())
					return;
				try {
					Attachment a = new Attachment();
					a.setDescription(descField.getText());
					a.setStrainId(id);
					byte[] b = IOUtils.toByteArray(new FileInputStream(file));
					a.setData(b);
					a.setFileName(file.getName());
					DBUtil db = DBUtil.getInstance();
					if (db.saveAttachment(a)) {
						descField.setText("");
						showMessage2(Color.BLUE, "您已成功保存附件！");
						refresh2();
					} else {
						showMessage2(Color.RED, "未能保存附件，请稍候再试！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.add(uploadButton);
	}

	public void showMessage2(Color color, String message) {

	}

	public void refresh2() {

	}

	private boolean isImage(String fileName) {
		fileName = fileName.toLowerCase();
		String[] imageExt = { ".png", ".jpg", ".gif", ".bmp" };
		for (String s : imageExt) {
			if (fileName.endsWith(s))
				return true;
		}
		return false;
	}

}
