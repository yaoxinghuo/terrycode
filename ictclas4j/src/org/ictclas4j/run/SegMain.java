package org.ictclas4j.run;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.ictclas4j.segment.Segment;

/**
 * Copyright 2007.6.1 ���²���sinboy��
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 */

public class SegMain {
	public static Segment seg = Segment.getInstance(1);

	static Logger logger = Logger.getLogger(SegMain.class);

	public static void main(String[] args) {
		SegMain sm = new SegMain();
		sm.initFrm();
		// String[] source = {
		// "2006��¹����籭�˷�֮һ��������Ҫ��ʼ",
		// "���ݣ����羯�조Ѳ�ߡ�BBS�Ͳ���",
		// "18ʱ42�ֵ�������Ҫ��ʼ��",
		// "���²�ס������Է",
		// "���²��ں���",
		// "���۲��ܶ�",
		// "�й�����Ӵ�վ������",
		// "���ǣӣȣͣ��������͵��ӻ���˹�ҵ��ƴ�"};
		// for (int i = 0; i < source.length; i++) {
		// SegResult sr = seg.split(source[i]);
		// System.out.println("time:" + sr.getSpendTime() + " " +
		// sr.getFinalResult());
		// }

		// try {
		// int count = 0;
		// long times = 0;
		// long bytes = 0;
		// int segPathCount = 1;
		// int forCount = 1;
		// seg.setSegPathCount(segPathCount);
		// ArrayList<String> testCases =
		// GFString.readTxtFile2("test\\case1.txt");
		// for (int i = 0; i < forCount; i++) {
		// for (String src : testCases) {
		// SegResult sr = SegMain.seg.split(src);
		// count++;
		// bytes += src.getBytes().length;
		// times += sr.getSpendTime();
		// logger.info("[time:" + sr.getSpendTime() + "ms]:\n" +
		// sr.getFinalResult());
		// }
		// logger.info("i:"+i+"\ntotal_count:" + count + "\ntotal_time:" + times
		// + "\ntotal_bytes:" + bytes + "\navg_time:"
		// + (times / count) + "\navg_bytes:" + (bytes / times) + "b/ms");
		// }
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
	}

	private void initFrm() {
		FrmMain frm = FrmMain.getInstance();
		JFrame jf = frm.getJFrame();
		jf.setSize(800, 600);
		jf.setVisible(true);
	}

}