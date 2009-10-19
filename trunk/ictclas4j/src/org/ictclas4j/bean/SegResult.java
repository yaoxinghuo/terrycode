package org.ictclas4j.bean;

import java.util.ArrayList;
import java.util.TreeMap;

import org.ictclas4j.utility.POSTag;
import org.ictclas4j.utility.Utility;

public class SegResult {

	private long startTime;

	private String rawContent;// 原始分词内容

	private ArrayList<MidResult> mrList;// 中间结果

	private String finalResult;// 最终分词结果

	private ArrayList<WordResultBean> result = new ArrayList<WordResultBean>();

	public void setResult(ArrayList<WordResultBean> result) {
		this.result = result;
	}

	public ArrayList<WordResultBean> getResult() {
		return result;
	}

	public TreeMap<Integer, WordResultBean> getPosResult() {
		TreeMap<Integer, WordResultBean> results = new TreeMap<Integer, WordResultBean>();
		int index = 0;
		for (int i = 0; i < result.size(); i++) {
			WordResultBean word = result.get(i);
			if (i != result.size() - 1 && Utility.posStringToInt(word.getProperty()) == POSTag.NUM
					&& Utility.posStringToInt(result.get(i + 1).getProperty()) == POSTag.QUAN) {
				WordResultBean newResultBean = new WordResultBean();
				newResultBean.setWord(word.getWord() + result.get(i + 1).getWord());
				newResultBean.setProperty(Utility.posIntToString(POSTag.NOUN));
				results.put(index, newResultBean);
				i++;
			} else
				results.put(index, word);
			index++;
		}
		return results;
	}

	public SegResult(String rawContent) {
		this.rawContent = rawContent;
		startTime = System.currentTimeMillis();
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public ArrayList<MidResult> getMrList() {
		return mrList;
	}

	public void setMrList(ArrayList<MidResult> mrList) {
		this.mrList = mrList;
	}

	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

	public long getSpendTime() {
		return System.currentTimeMillis() - startTime;
	}

	public void addMidResult(MidResult mr) {
		if (mrList == null)
			mrList = new ArrayList<MidResult>();
		if (mr != null)
			mrList.add(mr);
	}

	public String toHTML() {
		StringBuffer html = new StringBuffer();

		if (rawContent != null) {
			html.append("原文内容：");
			html.append("<table border=\"1\" width=\"100%\"><tr><td width=\"100%\">");
			html.append(rawContent);
			html.append("</td></tr></table>");

			if (mrList != null) {
				for (MidResult mr : mrList) {
					html.append(mr.toHTML());
				}
			}

			if (finalResult != null) {
				html.append("<p>最终分词结果：");
				html.append("<table border=\"1\" width=\"100%\"><tr><td width=\"100%\">");
				html.append("<font color=\"blue\" size=6><b>" + finalResult + "</b></font>");
				html.append("</td></tr></table>");
			}
		}

		return html.toString();

	}
}
