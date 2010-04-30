package com.terry.straincatalog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.db4o.ObjectSet;
import com.terry.straincatalog.db.DBUtil;
import com.terry.straincatalog.model.Attachment;
import com.terry.straincatalog.model.Strain;
import com.terry.straincatalog.util.SpringUtil;
import com.terry.straincatalog.util.StringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Apr 30, 2010 10:19:31 AM
 */
public class DetailFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698354521055742493L;

	private static final String[] LABELS = { "中文菌名*", "拉丁学名*", "菌种描述", "培养基",
			"活化方法", "菌株来源*", "物理编号", "保藏编号*", "现保藏位置", "保藏时间", "鉴定方法", "鉴定时间",
			"备注" };

	private JTextField[] textFields;
	private JTextArea[] textAreas;
	private JLabel messageLabel;
	private JButton button;
	private JTabbedPane tabPanel;
	private Box box;
	private int mode = 0;
	private String id;

	public DetailFrame() {
		super("Strain Catalog");
		setLocationByPlatform(true);
		setResizable(false);

		Container container = this.getContentPane();

		JPanel northPanel = new JPanel();

		messageLabel = new JLabel();
		Font font = new Font(null, Font.BOLD, 14);
		messageLabel.setFont(font);
		northPanel.add(messageLabel);
		container.add(northPanel, BorderLayout.NORTH);

		tabPanel = new JTabbedPane();

		int numPairs = LABELS.length;
		textFields = new JTextField[numPairs - 3];
		textAreas = new JTextArea[3];

		int fc = 0;
		int ac = 0;
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(LABELS[i] + ":", JLabel.TRAILING);
			p.add(l);
			if (i == 2 || i == 10 || i == 12) {
				textAreas[ac] = new JTextArea(5, 20);
				l.setLabelFor(textAreas[ac]);
				p.add(textAreas[ac]);
				ac++;
			} else {
				textFields[fc] = new JTextField(15);
				l.setLabelFor(textFields[fc]);
				p.add(textFields[fc]);
				fc++;
			}
		}
		SpringUtil.makeCompactGrid(p, numPairs, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		p.setOpaque(true);

		tabPanel.add(new JScrollPane(p), "基本信息");
		tabPanel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (mode == 1 && tabPanel.getSelectedIndex() == 1) {
					showMessage(Color.ORANGE, "只有在修改模式下才能更新附件！");
				}

			}
		});
		JPanel attachementPanel = new JPanel();
		box = Box.createVerticalBox();
		attachementPanel.add(box);

		tabPanel.add(new JScrollPane(attachementPanel), "附件信息");

		container.add(tabPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		button = new JButton("关闭");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
				case 1:
				case 2:
					String chineseName = textFields[0].getText();
					String latinName = textFields[1].getText();
					String strainSources = textFields[4].getText();
					String accessionNumber = textFields[6].getText();
					// String remark = remarkArea.getText();
					if (checkValuesEmpty(chineseName, latinName, strainSources,
							accessionNumber)) {
						showMessage(Color.ORANGE, "带*的项不能为空！");
						return;
					}
					DBUtil db = DBUtil.getInstance();
					if (mode == 1) {
						if (db.saveStrain(getStrainFromForm())) {
							showMessage(Color.BLUE, "您已成功保存菌种！");
							clearValues();
						} else {
							showMessage(Color.RED, "未能保存菌种，请稍候再试！");
						}
					} else {
						if (db.updateStrain(id, getStrainFromForm())) {
							showMessage(Color.BLUE, "您已成功更新菌种！");
						} else {
							showMessage(Color.RED, "未能更新菌种，请稍候再试！");
						}
					}
					break;

				default:
					setVisible(false);
					break;
				}

			}
		});
		panel.add(button);
		container.add(panel, BorderLayout.SOUTH);
	}

	public void setMode(int mode) {
		this.mode = mode;
		switch (mode) {
		case 1:
			id = null;
			button.setText("新增");
			clearValues();
			break;
		case 2:
			button.setText("修改");
			break;
		default:
			button.setText("索取登记表（暂未实现）");
			break;
		}
	}

	private boolean checkValuesEmpty(String... strings) {
		for (String s : strings) {
			if (StringUtil.isNullOrEmpty(s))
				return true;
		}
		return false;
	}

	private void clearValues() {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setText("");
		}
		for (int i = 0; i < textAreas.length; i++) {
			textAreas[i].setText("");
		}
		textFields[0].requestFocus();
	}

	public void setValues(Strain s) {
		id = s.getId();

		textFields[0].setText(s.getChineseName());
		textFields[1].setText(s.getLatinName());
		textFields[2].setText(s.getCultureMeduim());
		textFields[3].setText(s.getActivationMethod());
		textFields[4].setText(s.getStrainSources());
		textFields[5].setText(s.getPhysicalNumber());
		textFields[6].setText(s.getAccessionNumber());
		textFields[7].setText(s.getPresentLocation());
		textFields[8].setText(s.getPreservation());
		textFields[9].setText(s.getIdentificationTime());

		textAreas[0].setText(s.getStrainDescribe());
		textAreas[1].setText(s.getIdentificationMethod());
		textAreas[2].setText(s.getRemark());

	}

	@Override
	public void setVisible(boolean v) {
		super.setVisible(v);
		if (v) {
			messageLabel.setText(" ");
			showAttachment();
		}
	}

	private void showMessage(Color color, String message) {
		messageLabel.setForeground(color);
		messageLabel.setText(message);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				messageLabel.setText(" ");

			}
		}, 10000);
	}

	private Strain getStrainFromForm() {
		Strain s = new Strain();
		s.setChineseName(textFields[0].getText());
		s.setLatinName(textFields[1].getText());
		s.setCultureMeduim(textFields[2].getText());
		s.setActivationMethod(textFields[3].getText());
		s.setStrainSources(textFields[4].getText());
		s.setPhysicalNumber(textFields[5].getText());
		s.setAccessionNumber(textFields[6].getText());
		s.setPresentLocation(textFields[7].getText());
		s.setPreservation(textFields[8].getText());
		s.setIdentificationTime(textFields[9].getText());

		s.setStrainDescribe(textAreas[0].getText());
		s.setIdentificationMethod(textAreas[1].getText());
		s.setRemark(textAreas[2].getText());
		return s;
	}

	private void showAttachment() {
		box.removeAll();

		if (id != null) {
			box.add(new AttachmentPanel(id) {

				/**
				 * 
				 */
				private static final long serialVersionUID = -2390300941034610281L;

				@Override
				public void showMessage2(Color color, String message) {
					showMessage(color, message);
				}

				@Override
				public void refresh2() {
					showAttachment();
				}
			});
			DBUtil db = DBUtil.getInstance();
			ObjectSet<Attachment> as = db.getAttachmentsByStrainId(id);
			for (Attachment a : as) {
				box.add(new AttachmentPanel(a) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 7297399041848565550L;

					@Override
					public void showMessage2(Color color, String message) {
						showMessage(color, message);
					}

					@Override
					public void refresh2() {
						showAttachment();
					}
				});
			}
		}
	}
}
