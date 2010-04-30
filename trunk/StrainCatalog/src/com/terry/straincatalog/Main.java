package com.terry.straincatalog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectSet;
import com.terry.straincatalog.db.DBUtil;
import com.terry.straincatalog.model.Strain;
import com.terry.straincatalog.util.SpringUtil;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-4-29 下午04:14:45
 */
public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 798401055604155823L;

	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*
				 * 使界面具有金属外观
				 */
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				JFrame.setDefaultLookAndFeelDecorated(true);
				Toolkit.getDefaultToolkit().setDynamicLayout(true);
				System.setProperty("sun.awt.noerasebackground", "true");
				try {
					UIManager.setLookAndFeel(new MetalLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				try {
					new Main().setVisible(true);
				} catch (Exception ex) {
				}
			}
		});
	}

	public Main() throws Exception {
		super("Strain Catalog");
		initComponents();
	}

	private JTextField[] textFields;
	private JButton searchButton;
	private JButton modifyButton;
	private JButton newButton;
	private JButton deleteButton;
	private JButton detailButton;
	private DefaultTableModel model;
	private JTable table;
	private DetailFrame detailFrame;
	private HashMap<Integer, String> ids = new HashMap<Integer, String>();
	private static final String[] LABELS = { "中文菌名", "拉丁学名", "菌株来源", "保藏编号",
			"备注" };

	private void initComponents() {
		Container container = this.getContentPane();

		detailFrame = new DetailFrame();
		detailFrame.setSize(800, 700);

		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				performSearch();
			}
		});

		int numPairs = LABELS.length;
		textFields = new JTextField[numPairs];

		JPanel panel = new JPanel();

		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(LABELS[i] + ":", JLabel.TRAILING);
			p.add(l);
			textFields[i] = new JTextField(20);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}
		// Lay out the panel.
		SpringUtil.makeCompactGrid(p, numPairs, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		p.setOpaque(true);

		panel.add(p);
		panel.add(searchButton);

		setLocationByPlatform(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}

		});

		container.add(panel, BorderLayout.NORTH);

		model = new DefaultTableModel();
		for (String label : LABELS) {
			model.addColumn(label);
		}
		table = new JTable(model);

		container.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		detailButton = new JButton("索取");
		detailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "请仅选择一条记录！", "警告",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				String id = ids.get(table.getSelectedRow());
				if (id != null) {
					Strain s = DBUtil.getInstance().getStrainById(id);
					if (s != null) {
						detailFrame.setMode(0);
						detailFrame.setValues(s);
						detailFrame.setVisible(true);
					}
				}
			}
		});
		southPanel.add(detailButton);

		modifyButton = new JButton("修改");
		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "请仅选择一条记录！", "警告",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				String id = ids.get(table.getSelectedRow());
				if (id != null) {
					Strain s = DBUtil.getInstance().getStrainById(id);
					if (s != null) {
						detailFrame.setMode(2);
						detailFrame.setValues(s);
						detailFrame.setVisible(true);
					}
				}
			}
		});
		newButton = new JButton("新增");
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				detailFrame.setMode(1);
				detailFrame.setVisible(true);
			}
		});
		deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "请至少选择一条记录！", "警告",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "您确定要删除选中的 "
						+ table.getSelectedRowCount() + " 条记录？", "确认删除",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					int count = 0;
					DBUtil db = DBUtil.getInstance();
					int[] rows = table.getSelectedRows();
					for (int i = 0; i < rows.length; i++) {
						String id = ids.get(rows[i]);
						if (id != null && db.deleteStrainById(id))
							count++;
					}
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "您未删除任何记录，请稍候再试！",
								"错误", JOptionPane.ERROR_MESSAGE);
					} else {
						performSearch();
						JOptionPane.showMessageDialog(null, "您已成功删除 " + count
								+ " 条记录！", "报告",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		southPanel.add(newButton);
		southPanel.add(modifyButton);
		southPanel.add(deleteButton);
		container.add(southPanel, BorderLayout.SOUTH);

		this.setSize(800, 600);
	}

	private void performSearch() {
		String chineseName = textFields[0].getText();
		String latinName = textFields[1].getText();
		String strainSources = textFields[2].getText();
		String accessionNumber = textFields[3].getText();
		String remark = textFields[4].getText();
		if (!checkSearchValid(chineseName, latinName, strainSources,
				accessionNumber, remark)) {
			JOptionPane.showMessageDialog(null, "请输入查询条件！", "警告",
					JOptionPane.WARNING_MESSAGE);
			textFields[0].requestFocus();
			return;
		}
		table.setEnabled(false);
		searchButton.setEnabled(false);
		if (table.getRowCount() > 0) {
			for (int i = table.getRowCount() - 1; i >= 0; i--)
				model.removeRow(i);
		}
		ids.clear();
		DBUtil db = DBUtil.getInstance();
		ObjectSet<Strain> bs = db.queryStrain(chineseName, latinName,
				strainSources, accessionNumber, remark);
		int count = 0;
		for (Strain b : bs) {
			String[] data = new String[] { b.getChineseName(),
					b.getLatinName(), b.getStrainSources(),
					b.getAccessionNumber(), b.getRemark() };
			model.addRow(data);
			ids.put(count++, b.getId());
		}
		table.setEnabled(true);
		searchButton.setEnabled(true);
	}

	private boolean checkSearchValid(String... strings) {
		for (String s : strings) {
			if (!s.trim().equals(""))
				return true;
		}
		return false;
	}

	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		setVisible(false);
		DBUtil.getInstance().closeDB();
	}
}
