package org.ictclas4j.bean;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create£ºAug 14, 2009 9:55:39 AM
 */
public class WordResultBean {
	private String word;

	private String property;
	
	private boolean stopWord = false;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setStopWord(boolean stopWord) {
		this.stopWord = stopWord;
	}

	public boolean isStopWord() {
		return stopWord;
	}
}
