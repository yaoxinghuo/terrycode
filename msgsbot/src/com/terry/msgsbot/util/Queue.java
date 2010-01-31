package com.terry.msgsbot.util;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-1-27 上午10:20:17
 */
public class Queue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3186022504236166883L;

	private LinkedList<String> ll = new LinkedList<String>();

	private int size = 10;

	public int getSize() {
		return ll.size();
	}

	public String getLink(int count) {
		return ll.get(ll.size() - count);
	}

	public String removeLink(int count) {
		return ll.remove(ll.size() - count);
	}

	public Queue(int size) {
		this.size = size;
	}

	public void put(String o) {
		if (ll.contains(o))
			ll.remove(o);
		ll.addLast(o);
		if (ll.size() > size) {
			int len = ll.size() - size;
			for (int i = 0; i < len; i++)
				ll.removeFirst();
		}
	}

	public void clear() {
		ll.clear();
	}

	public String statics() {
		if (ll.size() == 0)
			return "历史记录为空";
		StringBuffer sb = new StringBuffer("最近历史记录");
		int size = ll.size();
		for (int i = size - 1; i >= 0; i--) {
			String link = ll.get(i);
			sb.append("\r\n");
			int count = size - i;
			sb.append(count).append(" ");
			sb.append(link);

		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Queue q = new Queue(10);
		System.out.println(q.statics());
	}
}
