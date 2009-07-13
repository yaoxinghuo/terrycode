package com.terry.costnote.client;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 11, 2009 8:12:50 PM
 */
public class Item extends BaseTreeModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875306552853182048L;

	public Item() {

	}

	public Item(String id, String name, String iconStyle) {
		set("id", id);
		set("name", name);
		set("icon", iconStyle);
	}
}
